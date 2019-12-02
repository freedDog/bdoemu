package com.bdoemu.login.databaseCollections;

import com.bdoemu.commons.database.mongo.ADatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseLockInfo;
import com.bdoemu.commons.rmi.model.Macros;
import com.bdoemu.login.idfactory.LSIDStorageType;
import com.bdoemu.login.idfactory.LoginServerIDFactory;
import com.bdoemu.login.models.db.Account;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AccountsDbCollection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:26
 * VERSION 1.0
 */

@DatabaseCollection
public class AccountsDbCollection extends ADatabaseCollection<Account,LoginServerIDFactory>{
    private static final Logger log= LoggerFactory.getLogger(AccountsDbCollection.class);
    private AccountsDbCollection(final Class<Account> clazz) {
        super(clazz,"accounts");
        this.addLockInfo(new DatabaseLockInfo(LSIDStorageType.ACCOUNT,"_id"));
    }

    public long addCash(final long n, final long n2) {
        try {
            return ((BasicDBObject)(this.collection.findAndModify(new BasicDBObject("",n),null,null,false,new BasicDBObject("",n2),true,false))).getLong("");
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("add Cash Error", ex);
            return 0L;
        }
    }

    public long addCash(final String s, final long n) {
        try {
            return ((BasicDBObject)(this.collection.findAndModify(new BasicDBObject("",s),null,null,false,new BasicDBObject("",n),true,false))).getLong("");
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("add cash Error", ex);
            return 0L;
        }
    }

    public long getCash(final long n) {
        try {
            final BasicDBObject basicDBObject = new BasicDBObject("", n);
            final BasicDBObject basicDBObject2 = new BasicDBObject();
            basicDBObject2.put("", true);
            final BasicDBObject basicDBObject3 = (BasicDBObject)this.collection.findOne(basicDBObject, basicDBObject2);
            if (basicDBObject3 == null) {
                return 0L;
            }
            return basicDBObject3.getLong("");
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("getCash Error", ex);
            return 0L;
        }
    }

    public void updateLastConnectionInfo(final Account account) {
        try {
            final BasicDBObject basicDBObject = new BasicDBObject("",account);
            final BasicDBObject basicDBObject2 = new BasicDBObject();
            basicDBObject2.append("", account.getAccountName());
            this.collection.update(basicDBObject, new BasicDBObject("", basicDBObject2), true, true);
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("updateLastConnectionInfo Errro", (Throwable)ex);
        }
    }

    public void updateMacrosses(final long n, final Macros[] array) {
        try {
            final BasicDBObject basicDBObject = (BasicDBObject)this.collection.findOne(new BasicDBObject("", n));
            if (basicDBObject == null) {
                return;
            }
            final BasicDBObject basicDBObject2 = new BasicDBObject("", n);
            final Object value = basicDBObject.get("");
            BasicDBList list;
            if (value == null) {
                list = new BasicDBList();
            }
            else {
                list = (BasicDBList)value;
            }
            for (int i = 0; i < 10; ++i) {
                final BasicDBObject basicDBObject3 = (BasicDBObject)((value != null) ? list.get(i) : new BasicDBObject());
                basicDBObject3.append("", i);
                if (array != null && array[i] != null) {
                    basicDBObject3.append("",  array[i].getChatType());
                    basicDBObject3.append("", array[i].getMacrosData());
                }
                else {
                    basicDBObject3.append("", 3);
                    basicDBObject3.append("", "");
                    list.add(basicDBObject3);
                }
            }
            this.collection.update(basicDBObject2,  new BasicDBObject("", list), false, false);
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("update Macrosses error", ex);
        }
    }

    public void updateCharacterSlots(final long n, final int n2) {
        try {
            this.collection.update(new BasicDBObject("", n),new BasicDBObject("", new BasicDBObject("", n2)), true, true);
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("update CharacterSlots Error ", ex);
        }
    }

    public void updateGameOption(final long n, final String s) {
        try {
            final BasicDBObject basicDBObject = new BasicDBObject("" , n);
            final BasicDBObject basicDBObject2 = new BasicDBObject();
            basicDBObject2.append("", s);
            this.collection.update(basicDBObject, new BasicDBObject("",new BasicDBObject("", basicDBObject2)), true, true);
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("", ex);
        }
    }

    public void updateUiInfo(final long n, final String s) {
        try {
            this.collection.update(new BasicDBObject("",  n), new BasicDBObject("", new BasicDBObject("", new BasicDBObject("", s))), true, true);
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("update UiInfo Error", ex);
        }
    }

    public void updatePin(final Account account) {
        try {
            this.collection.update((DBObject)new BasicDBObject("", account.getObjectId()), new BasicDBObject("", new BasicDBObject("",account.getPin())), true, true);
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("",ex);
        }
    }

    public synchronized boolean updateFamily(final long n, final String s) {
        try {
            final BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("", 2);
            if (this.collection.findOne(basicDBObject) != null) {
                return false;
            }
            this.collection.update(new BasicDBObject("", n), new BasicDBObject("", new BasicDBObject("",s)), true, true);
        }
        catch (MongoException ex) {
            AccountsDbCollection.log.error("", ex);
        }
        return true;
    }

    public static AccountsDbCollection getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final AccountsDbCollection INSTANCE = new AccountsDbCollection(Account.class);
    }

}
