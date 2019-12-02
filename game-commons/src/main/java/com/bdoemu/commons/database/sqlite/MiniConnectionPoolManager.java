package com.bdoemu.commons.database.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MiniConnectionPoolManager
 * @Description 小型连接池管理器
 * @Author JiangBangMing
 * @Date 2019/6/24 18:12
 * VERSION 1.0
 */

public class MiniConnectionPoolManager {
    private static final Logger log= LoggerFactory.getLogger((Class)MiniConnectionPoolManager.class);;
    private ConnectionPoolDataSource dataSource;
    private int maxConnections;
    private long timeoutMs;
    private Semaphore semaphore;
    private PoolConnectionEventListener poolConnectionEventListener;
    private LinkedList<PooledConnection> recycledConnections;
    private int activeConnections;
    private boolean isDisposed;
    private boolean doPurgeConnection;
    private PooledConnection connectionInTransition;

    public MiniConnectionPoolManager(final ConnectionPoolDataSource dataSource, final int maxConnections) {
        this(dataSource, maxConnections, 60);
    }

    public MiniConnectionPoolManager(final ConnectionPoolDataSource dataSource, final int maxConnections, final int timeout) {
        this.dataSource = dataSource;
        this.maxConnections = maxConnections;
        this.timeoutMs = timeout * 1000L;
        if (maxConnections < 1) {
            throw new IllegalArgumentException("Invalid maxConnections value.");
        }
        this.semaphore = new Semaphore(maxConnections, true);
        this.recycledConnections = new LinkedList<PooledConnection>();
        this.poolConnectionEventListener = new PoolConnectionEventListener();
    }

    public synchronized void dispose() throws SQLException {
        if (this.isDisposed) {
            return;
        }
        this.isDisposed = true;
        SQLException e = null;
        while (!this.recycledConnections.isEmpty()) {
            final PooledConnection pconn = this.recycledConnections.remove();
            try {
                pconn.close();
            }
            catch (SQLException e2) {
                if (e != null) {
                    continue;
                }
                e = e2;
            }
        }
        if (e != null) {
            throw e;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.getConnection2(this.timeoutMs);
    }

    private Connection getConnection2(final long timeoutMs) throws SQLException {
        synchronized (this) {
            if (this.isDisposed) {
                throw new IllegalStateException("Connection pool has been disposed.");
            }
        }
        try {
            if (!this.semaphore.tryAcquire(timeoutMs, TimeUnit.MILLISECONDS)) {
                throw new TimeoutException();
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while waiting for a database connection.", e);
        }
        boolean ok = false;
        try {
            final Connection conn = this.getConnection3();
            ok = true;
            return conn;
        }
        finally {
            if (!ok) {
                this.semaphore.release();
            }
        }
    }

    private synchronized Connection getConnection3() throws SQLException {
        if (this.isDisposed) {
            throw new IllegalStateException("Connection pool has been disposed.");
        }
        PooledConnection pconn;
        if (!this.recycledConnections.isEmpty()) {
            pconn = this.recycledConnections.remove();
        }
        else {
            pconn = this.dataSource.getPooledConnection();
            pconn.addConnectionEventListener(this.poolConnectionEventListener);
        }
        Connection conn;
        try {
            this.connectionInTransition = pconn;
            conn = pconn.getConnection();
        }
        finally {
            this.connectionInTransition = null;
        }
        ++this.activeConnections;
        this.assertInnerState();
        return conn;
    }

    public Connection getValidConnection() {
        long time = System.currentTimeMillis();
        final long timeoutTime = time + this.timeoutMs;
        int triesWithoutDelay = this.getInactiveConnections() + 1;
        while (true) {
            final Connection conn = this.getValidConnection2(time, timeoutTime);
            if (conn != null) {
                return conn;
            }
            if (--triesWithoutDelay <= 0) {
                triesWithoutDelay = 0;
                try {
                    Thread.sleep(250L);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException("Interrupted while waiting for a valid database connection.", e);
                }
            }
            time = System.currentTimeMillis();
            if (time >= timeoutTime) {
                throw new TimeoutException("Timeout while waiting for a valid database connection.");
            }
        }
    }

    private Connection getValidConnection2(final long time, final long timeoutTime) {
        long rtime = Math.max(1L, timeoutTime - time);
        Connection conn;
        try {
            conn = this.getConnection2(rtime);
        }
        catch (SQLException e) {
            return null;
        }
        rtime = timeoutTime - System.currentTimeMillis();
        final int rtimeSecs = Math.max(1, (int)((rtime + 999L) / 1000L));
        try {
            if (conn.isValid(rtimeSecs)) {
                return conn;
            }
        }
        catch (SQLException ex) {}
        this.purgeConnection(conn);
        return null;
    }

    private synchronized void purgeConnection(final Connection conn) {
        try {
            this.doPurgeConnection = true;
            conn.close();
        }
        catch (SQLException ex) {}
        finally {
            this.doPurgeConnection = false;
        }
    }

    private synchronized void recycleConnection(final PooledConnection pconn) {
        if (this.isDisposed || this.doPurgeConnection) {
            this.disposeConnection(pconn);
            return;
        }
        if (this.activeConnections <= 0) {
            throw new AssertionError();
        }
        --this.activeConnections;
        this.semaphore.release();
        this.recycledConnections.add(pconn);
        this.assertInnerState();
    }

    private synchronized void disposeConnection(final PooledConnection pconn) {
        pconn.removeConnectionEventListener(this.poolConnectionEventListener);
        if (!this.recycledConnections.remove(pconn) && pconn != this.connectionInTransition) {
            if (this.activeConnections <= 0) {
                throw new AssertionError();
            }
            --this.activeConnections;
            this.semaphore.release();
        }
        this.closeConnectionAndIgnoreException(pconn);
        this.assertInnerState();
    }

    private void closeConnectionAndIgnoreException(final PooledConnection pconn) {
        try {
            pconn.close();
        }
        catch (SQLException e) {
            MiniConnectionPoolManager.log.error("Error while closing database connection: {}", (Object)e.toString());
        }
    }

    private synchronized void assertInnerState() {
        if (this.activeConnections < 0) {
            throw new AssertionError();
        }
        if (this.activeConnections + this.recycledConnections.size() > this.maxConnections) {
            throw new AssertionError();
        }
        if (this.activeConnections + this.semaphore.availablePermits() > this.maxConnections) {
            throw new AssertionError();
        }
    }

    public synchronized int getActiveConnections() {
        return this.activeConnections;
    }

    public synchronized int getInactiveConnections() {
        return this.recycledConnections.size();
    }

    public static class TimeoutException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;

        public TimeoutException() {
            super("Timeout while waiting for a free database connection.");
        }

        public TimeoutException(final String msg) {
            super(msg);
        }
    }

    private class PoolConnectionEventListener implements ConnectionEventListener
    {
        @Override
        public void connectionClosed(final ConnectionEvent event) {
            final PooledConnection pconn = (PooledConnection)event.getSource();
            MiniConnectionPoolManager.this.recycleConnection(pconn);
        }

        @Override
        public void connectionErrorOccurred(final ConnectionEvent event) {
            final PooledConnection pconn = (PooledConnection)event.getSource();
            MiniConnectionPoolManager.this.disposeConnection(pconn);
        }
    }
}
