package com.bdoemu.login.models.db;

import com.bdoemu.commons.database.mongo.JSONable;
import com.bdoemu.commons.model.enums.ELogEntryType;
import com.bdoemu.commons.model.xmlrpc.XMLRPCable;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcLog;
import com.bdoemu.login.idfactory.LSIDStorageType;
import com.bdoemu.login.idfactory.LoginServerIDFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName Log
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:38
 * VERSION 1.0
 */

public class Log extends JSONable implements XMLRPCable {

    private long id;
    private long date;
    private ELogEntryType logType;
    private long accountId;
    private String ip;
    private String comment;

    public Log(final long accountId, final ELogEntryType logType, final String ip, final String comment) {
        this.id = LoginServerIDFactory.getInstance().nextId(LSIDStorageType.LOG);
        this.date = System.currentTimeMillis();
        this.logType = logType;
        this.accountId = accountId;
        this.ip = ip;
        this.comment = comment;
    }

    public Log(final BasicDBObject basicDBObject) {
        this.id = basicDBObject.getLong("");
        this.date = basicDBObject.getLong("");
        this.logType = ELogEntryType.values()[basicDBObject.getInt("")];
        this.accountId = basicDBObject.getLong("");
        this.ip = basicDBObject.getString("");
        this.comment = basicDBObject.getString("");
    }

    @Override
    public long getObjectId() {
        return this.id;
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder basicDBObjectBuilder = new BasicDBObjectBuilder();
        basicDBObjectBuilder.append("", this.id);
        basicDBObjectBuilder.append("", this.date);
        basicDBObjectBuilder.append("", this.logType.ordinal());
        basicDBObjectBuilder.append("", this.accountId);
        basicDBObjectBuilder.append("", (Object)this.ip);
        basicDBObjectBuilder.append("", (Object)this.comment);
        return basicDBObjectBuilder.get();
    }
    @Override
    public XmlRpcLog toXMLRpcObject(final String message) {
        final XmlRpcLog xmlRpcLog = new XmlRpcLog();
        xmlRpcLog.setId(this.id);
        xmlRpcLog.setDate(this.date);
        xmlRpcLog.setLogType(this.logType);
        xmlRpcLog.setAccountId(this.accountId);
        xmlRpcLog.setIp(this.ip);
        xmlRpcLog.setComment(this.comment);
        if (message != null) {
            xmlRpcLog.setMessage(message);
        }
        return xmlRpcLog;
    }
}
