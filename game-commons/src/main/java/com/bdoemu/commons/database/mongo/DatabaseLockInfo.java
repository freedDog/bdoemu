package com.bdoemu.commons.database.mongo;

import com.mongodb.BasicDBObject;
import sun.reflect.CallerSensitive;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName DatabaseLockInfo
 * @Description 数据库锁信息
 * @Author JiangBangMing
 * @Date 2019/6/20 19:35
 * VERSION 1.0
 */

public class DatabaseLockInfo {

    /**存储类型*/
    private Enum storageType;
    /**对象路径*/
    private String objectPath;
    /**对象id 字段*/
    private String objectIdField;

    public DatabaseLockInfo(Enum storageType,String objectPath,String objectIdField){
        this.storageType=storageType;
        this.objectPath=objectPath;
        this.objectIdField=objectIdField;
        System.out.println("Locking storage:"+storageType.name()+";objectPath: "+objectPath+"; objectIdField: "+objectIdField);
    }

    public DatabaseLockInfo(Enum storageType,String objectIdField){
        this.storageType=storageType;
        this.objectIdField=objectIdField;
        System.out.println("Locking storage: "+storageType.name()+"; objectIdField: "+objectIdField);
    }

    public BasicDBObject getFilterFields(BasicDBObject paramBasicDBObject) {
        BasicDBObject basicDBObject = new BasicDBObject();
        if (getObjectPath() != null) {
            String str = getObjectPath();
            if (str.contains("*")) {
                str = str.substring(0, str.indexOf("*") - 1);
            }
            if (getObjectIdField() != null) {
                str = str + "." + getObjectIdField();
            }
            basicDBObject.put(str, Boolean.valueOf(true));
        } else {
            basicDBObject.put(getObjectIdField(), Boolean.valueOf(true));
        }
        if (paramBasicDBObject != null && paramBasicDBObject.size() > 0) {
            for (String str : paramBasicDBObject.keySet()) {
                Object object = paramBasicDBObject.get(str);
                if (object instanceof List) {
                    Iterator<BasicDBObject> iterator=((List) object).iterator();
                    while (iterator.hasNext()){
                        iterator.next().entrySet().forEach(entry -> basicDBObject.put(entry.getKey(),Boolean.valueOf(true)));
                    }
                }else{
                    basicDBObject.put(str, Boolean.valueOf(true));
                }

            }
        }
        return basicDBObject;
    }


    public Enum getStorageType() {
        return this.storageType;
    }

    public String getObjectPath() {
        return this.objectPath;
    }

    public String getObjectIdField() {
        return this.objectIdField;
    }

    public void setStorageType(Enum paramEnum) {
        this.storageType = paramEnum;
    }

    public void setObjectPath(String paramString) {
        this.objectPath = paramString;
    }

    public void setObjectIdField(String paramString) {
        this.objectIdField = paramString;
    }
     @Override
     public boolean equals(Object paramObject) {
        if (paramObject == this) {
            return true;
        }
        if (!(paramObject instanceof DatabaseLockInfo)) {
            return false;
        }
        DatabaseLockInfo databaseLockInfo = (DatabaseLockInfo)paramObject;
        if (!databaseLockInfo.canEqual(this)) {
            return false;
        }
        Enum enum1 = getStorageType();
        Enum enum2 = databaseLockInfo.getStorageType();

        if ((enum1 == null) ? (
                enum2 == null) :
                enum1.equals(enum2)) {
            String str1 = getObjectPath();
            String str2 = databaseLockInfo.getObjectPath();
            if ((str1 == null) ? (
                    str2 == null) :
                    str1.equals(str2)) {
                String str3 = getObjectIdField();
                String str4 = databaseLockInfo.getObjectIdField();
                if (str3 == null) {
                    if (str4 == null) {
                        return true;
                    }
                }
                else if (str3.equals(str4)) {
                    return true;
                }

                return false;
            }
            return false;
        }
        return false; } protected boolean canEqual(Object paramObject) { return paramObject instanceof DatabaseLockInfo;
    }

    @Override
    public int hashCode() {
        final int n = 1;
        final Enum storageType = this.getStorageType();
        final int n2 = n * 59 + ((storageType == null) ? 43 : storageType.hashCode());
        final String objectPath = this.getObjectPath();
        final int n3 = n2 * 59 + ((objectPath == null) ? 43 : objectPath.hashCode());
        final String objectIdField = this.getObjectIdField();
        return n3 * 59 + ((objectIdField == null) ? 43 : objectIdField.hashCode());
    }
    @Override
    public String toString() {
        return "DatabaseLockInfo(storageType=" + getStorageType() + ", objectPath=" + getObjectPath() + ", objectIdField=" + getObjectIdField() + ")";
    }


}
