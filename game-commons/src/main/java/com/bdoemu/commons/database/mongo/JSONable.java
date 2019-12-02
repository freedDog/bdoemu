package com.bdoemu.commons.database.mongo;


import com.mongodb.DBObject;
/**
 * @ClassName JSONable
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/20 12:29
 * VERSION 1.0
 */

public abstract class JSONable {

    public long getObjectId() {
        return 0L;
    }

    public DBObject toDBObject() {
        return null;
    }
}
