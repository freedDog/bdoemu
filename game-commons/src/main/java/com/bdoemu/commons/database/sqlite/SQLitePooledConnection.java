package com.bdoemu.commons.database.sqlite;

import org.sqlite.SQLiteConnection;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.StatementEventListener;
import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @ClassName SQLitePooledConnection
 * @Description SQLite合用的连接
 * @Author JiangBangMing
 * @Date 2019/6/24 18:27
 * VERSION 1.0
 */

public class SQLitePooledConnection implements XAConnection, XAResource {

    private SQLiteConnection physicalConn;
    private volatile Connection handleConn;
    private ArrayList<ConnectionEventListener> listeners;
    private Properties config;

    private static <T> ArrayList<T> arrayList() {
        return new ArrayList<T>(4);
    }

    public SQLitePooledConnection(final SQLiteConnection physicalConn, final Properties config) {
        this.listeners = arrayList();
        this.physicalConn = physicalConn;
        this.config = config;
    }

    @Override
    public XAResource getXAResource() {
        return this;
    }

    @Override
    public void close() throws SQLException {
        final Connection lastHandle = this.handleConn;
        if (lastHandle != null) {
            this.listeners.clear();
            lastHandle.close();
        }
        if (this.physicalConn != null) {
            try {
                this.physicalConn.close();
            }
            finally {
                this.physicalConn = null;
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        final Connection lastHandle = this.handleConn;
        if (lastHandle != null) {
            lastHandle.close();
        }
        return this.handleConn = (Connection)new PooledJdbcConnection(this.physicalConn, this.config);
    }

    @Override
    public Xid[] recover(final int flag) throws XAException {
        throw new XAException("Not Implemented");
    }

    @Override
    public int prepare(final Xid xid) throws XAException {
        throw new XAException("Not Implemented");
    }

    @Override
    public void forget(final Xid xid) {
    }

    @Override
    public void rollback(final Xid xid) throws XAException {
        throw new XAException("Not Implemented");
    }

    @Override
    public void end(final Xid xid, final int flags) throws XAException {
        throw new XAException("Not Implemented");
    }

    @Override
    public void start(final Xid xid, final int flags) throws XAException {
        throw new XAException("Not Implemented");
    }

    @Override
    public void commit(final Xid xid, final boolean onePhase) throws XAException {
        throw new XAException("Not Implemented");
    }

    @Override
    public void addConnectionEventListener(final ConnectionEventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeConnectionEventListener(final ConnectionEventListener listener) {
        this.listeners.remove(listener);
    }

    void closedHandle() {
        final ConnectionEvent event = new ConnectionEvent(this);
        for (int i = this.listeners.size() - 1; i >= 0; --i) {
            final ConnectionEventListener listener = this.listeners.get(i);
            listener.connectionClosed(event);
        }
        this.handleConn = null;
    }

    @Override
    public int getTransactionTimeout() {
        return 0;
    }

    @Override
    public boolean setTransactionTimeout(final int seconds) {
        return false;
    }

    @Override
    public boolean isSameRM(final XAResource xares) {
        return xares == this;
    }

    @Override
    public void addStatementEventListener(final StatementEventListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeStatementEventListener(final StatementEventListener listener) {
        throw new UnsupportedOperationException();
    }

    private static String getFileName(final String url) {
        String fileName = url.substring("jdbc:sqlite:".length());
        if (fileName.startsWith("//")) {
            fileName = fileName.substring(2);
        }
        return fileName;
    }

    public static void closeSilently(final Statement stat) {
        if (stat != null) {
            try {
                stat.close();
            }
            catch (SQLException ex) {}
        }
    }

    class PooledJdbcConnection extends SQLiteConnection
    {
        private boolean isClosed;

        public PooledJdbcConnection(final SQLiteConnection conn, final Properties config) throws SQLException {
            super(conn.url(), getFileName(conn.url()), config);
        }
        @Override
        public synchronized void close() throws SQLException {
            if (!this.isClosed) {
                if (!this.getAutoCommit()) {
                    this.rollback();
                }
                SQLitePooledConnection.this.closedHandle();
                super.close();
                this.isClosed = true;
            }
        }
        @Override
        public synchronized boolean isClosed() throws SQLException {
            return this.isClosed || super.isClosed();
        }

        protected synchronized void checkClosed(final boolean write) {
        }
    }
}
