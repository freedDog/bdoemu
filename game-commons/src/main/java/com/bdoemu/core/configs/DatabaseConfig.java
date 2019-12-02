package com.bdoemu.core.configs;

import com.bdoemu.commons.config.annotation.ConfigComments;
import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;

/**
 * @ClassName DatabaseConfig
 * @Description 数据库配置
 * @Author JiangBangMing
 * @Date 2019/6/22 17:21
 * VERSION 1.0
 */

@ConfigFile(name = "configs/database.properties")
public class DatabaseConfig {
    @ConfigComments(comment = {"Use anonymous account for DB connect."})
    @ConfigProperty(name = "anonymous", value = "true")
    public static boolean ANONYMOUS;

    @ConfigComments(comment = {"The database Account."})
    @ConfigProperty(name = "databaseAccount", value = "admin")
    public static String ACCOUNT;

    @ConfigComments(comment = {"The database Password."})
    @ConfigProperty(name = "databasePassword", value = "12345")
    public static String PASSWORD;

    @ConfigComments(comment = {"The database's host address."})
    @ConfigProperty(name = "databaseHost", value = "localhost")
    public static String HOST;

    @ConfigComments(comment = {"The port on which the database is running."})
    @ConfigProperty(name = "databasePort", value = "27017")
    public static int PORT;

    @ConfigComments(comment = {"The database name."})
    @ConfigProperty(name = "databaseName", value = "gameserver")
    public static String DATABASE_NAME;

    @ConfigComments(comment = {"The database auth name."})
    @ConfigProperty(name = "databaseAuthName", value = "admin")
    public static String DATABASE_AUTH_NAME;

    @ConfigComments(comment = {"Use database logging."})
    @ConfigProperty(name = "allowDatabaseLogging", value = "true")
    public static boolean ALLOW_DATABASE_LOGGING;

    @ConfigComments(comment = {"The maximum number of connections allowed per host for this Mongo instance.", "Those connections will be kept in a pool when idle.", "Once the pool is exhausted, any operation requiring a connection will block waiting for an available connection.", "Default is 10."})
    @ConfigProperty(name = "connectionsPerHost", value = "10")
    public static int CONNECTIONS_PER_HOST;

    @ConfigComments(comment = {"This multiplier, multiplied with the connectionsPerHost setting, gives the maximum number of threads that", "may be waiting for a connection to become available from the pool.", "All further threads will get an exception right away.", "For example if connectionsPerHost is 10 and threadsAllowedToBlockForConnectionMultiplier is 5, then up to 50 threads can wait for a connection.", "Default is 5."})
    @ConfigProperty(name = "threadsAllowedToBlockForConnectionMultiplier", value = "5")
    public static int THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER;

    @ConfigComments(comment = {"The maximum wait time in milliseconds that a thread may wait for a connection to become available.", "Default is 120,000. A value of 0 means that it will not wait.  A negative value means to wait indefinitely."})
    @ConfigProperty(name = "maxWaitTime", value = "120000")
    public static int MAX_WAIT_TIME;

    @ConfigComments(comment = {"The connection timeout in milliseconds.  A value of 0 means no timeout.", "It is used solely when establishing a new connection", "Default is 10,000."})
    @ConfigProperty(name = "connectTimeout", value = "10000")
    public static int CONNECT_TIME_OUT;

    @ConfigComments(comment = {"Do mongodump while server starting."})
    @ConfigProperty(name = "backupAtStart", value = "false")
    public static boolean BACKUP_AT_START;
}
