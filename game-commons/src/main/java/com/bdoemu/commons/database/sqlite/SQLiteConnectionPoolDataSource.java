package com.bdoemu.commons.database.sqlite;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteDataSource;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import javax.sql.XAConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName SQLiteConnectionPoolDataSource
 * @Description 连接池数据源
 * @Author JiangBangMing
 * @Date 2019/6/24 18:15
 * VERSION 1.0
 */

public class SQLiteConnectionPoolDataSource extends SQLiteDataSource implements ConnectionPoolDataSource {

    @Override
    public PooledConnection getPooledConnection() throws SQLException {
        return this.getXAConnection();
    }

    @Override
    public PooledConnection getPooledConnection(final String user, final String password) throws SQLException {
        return this.getXAConnection(user, password);
    }

    public XAConnection getXAConnection() throws SQLException {
        return this.getXAConnection(null, null);
    }

    public XAConnection getXAConnection(final String user, final String password) throws SQLException {
        return new SQLitePooledConnection(this.getJdbcConnection(user, password), this.getConfig().toProperties());
    }

    public Connection getConnection() throws SQLException {
        return (Connection)this.getJdbcConnection(null, null);
    }

    public Connection getConnection(final String user, final String password) throws SQLException {
        return (Connection)this.getJdbcConnection(user, password);
    }

    private SQLiteConnection getJdbcConnection(final String user, final String password) throws SQLException {
        final String url = this.getUrl();
        String fileName = url.substring("jdbc:sqlite:".length());
        if (fileName.startsWith("//")) {
            fileName = fileName.substring(2);
        }
        this.getConfig().setJournalMode(SQLiteConfig.JournalMode.OFF);
        this.getConfig().setSynchronous(SQLiteConfig.SynchronousMode.OFF);
        return new SQLiteConnection(url, fileName, this.getConfig().toProperties());
    }
}
