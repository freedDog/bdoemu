package com.bdoemu.commons.database;

import com.bdoemu.core.configs.DatabaseConfig;
import com.bdoemu.core.startup.StartupComponent;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName DatabaseFactory
 * @Description 数据库工厂
 * @Author JiangBangMing
 * @Date 2019/6/22 17:16
 * VERSION 1.0
 */

@StartupComponent("Database")
public class DatabaseFactory {
    private static final Logger log = LoggerFactory.getLogger(DatabaseFactory.class);

    private MongoClient client;

    private static final AtomicReference<Object> instance = new AtomicReference();

    public static DatabaseFactory getInstance() {
       Object value = instance.get();
        if (value == null){
            synchronized (instance) {
                value = instance.get();
                if (value == null) {
                    DatabaseFactory actualValue = new DatabaseFactory();
                    value = (actualValue == null) ? instance : actualValue;
                    instance.set(value);
                }
            }
        }
        return (DatabaseFactory)((value == instance) ? null : value);
    }


    private DatabaseFactory() {
        try {
            MongoClientOptions.Builder configBuilder = new MongoClientOptions.Builder();
            configBuilder.connectTimeout(DatabaseConfig.CONNECT_TIME_OUT);
            configBuilder.connectionsPerHost(DatabaseConfig.CONNECTIONS_PER_HOST);
            configBuilder.maxWaitTime(DatabaseConfig.MAX_WAIT_TIME);
            configBuilder.threadsAllowedToBlockForConnectionMultiplier(DatabaseConfig.THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER);
            MongoClientOptions config = configBuilder.build();
            if (DatabaseConfig.ANONYMOUS) {
                this.client = new MongoClient(new ServerAddress(DatabaseConfig.HOST, DatabaseConfig.PORT), config);
            } else {

                MongoCredential credential = MongoCredential.createCredential(DatabaseConfig.ACCOUNT, DatabaseConfig.DATABASE_AUTH_NAME, DatabaseConfig.PASSWORD.toCharArray());
                this.client = new MongoClient(new ServerAddress(DatabaseConfig.HOST, DatabaseConfig.PORT), Collections.singletonList(credential), config);
            }
            if (DatabaseConfig.BACKUP_AT_START) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                    String command = "mongodump --host " + DatabaseConfig.HOST + " --port " + DatabaseConfig.PORT
                            + " --db " + DatabaseConfig.DATABASE_NAME + " --out ./mongo_backup/" + dateFormat.format(new Date());
                    if (!DatabaseConfig.ANONYMOUS) {
                        command = command + " --username " + DatabaseConfig.ACCOUNT + " --password " + DatabaseConfig.PASSWORD;
                    }
                    Process mongoDump = Runtime.getRuntime().exec(command);
                    log.info("Dumping {} to backup...", DatabaseConfig.DATABASE_NAME);
                    mongoDump.waitFor();
                    log.info("Dumped!");
                }
                catch (Exception e) {
                    log.error("Error while backup MongoDB!", e);
                }
            }
            log.info("Database factory connected to {}:{}/{}", new Object[] { DatabaseConfig.HOST, Integer.valueOf(DatabaseConfig.PORT), DatabaseConfig.DATABASE_NAME });
        } catch (Exception ex) {
            log.error("Error while init DatabaseFactory", ex);
        }
    }

    public DB getDatabase() {
        return this.client.getDB(DatabaseConfig.DATABASE_NAME);
    }

    public DB getDatabase(String dbName) {
        return this.client.getDB(dbName);
    }

    public void shutdown() {
        this.client.close();
    }
}
