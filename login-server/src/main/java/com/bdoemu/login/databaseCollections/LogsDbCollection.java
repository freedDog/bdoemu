package com.bdoemu.login.databaseCollections;

import com.bdoemu.commons.database.mongo.ADatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseLockInfo;
import com.bdoemu.login.idfactory.LSIDStorageType;
import com.bdoemu.login.idfactory.LoginServerIDFactory;
import com.bdoemu.login.models.db.Log;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @ClassName LogsDbCollection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:42
 * VERSION 1.0
 */

@DatabaseCollection
public class LogsDbCollection extends ADatabaseCollection<Log, LoginServerIDFactory> {

    private static final Logger log= LoggerFactory.getLogger(LogsDbCollection.class);

    private LogsDbCollection(final Class<Log> clazz) {
        super(clazz,"logs");
        this.addLockInfo(new DatabaseLockInfo(LSIDStorageType.LOG,"_id"));
    }

    public Collection<Log> loadByAccountId(final long n) {
        return super.load(new BasicDBObject("",n)).values();
    }

    public static LogsDbCollection getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder {
        static final LogsDbCollection INSTANCE = new LogsDbCollection(Log.class);
    }
}
