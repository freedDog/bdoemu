package com.bdoemu.login.databaseCollections;

import com.bdoemu.commons.database.mongo.ADatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseLockInfo;
import com.bdoemu.commons.model.enums.EBanCriteriaType;
import com.bdoemu.login.idfactory.LSIDStorageType;
import com.bdoemu.login.idfactory.LoginServerIDFactory;
import com.bdoemu.login.models.db.Ban;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BansDbCollection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:41
 * VERSION 1.0
 */

@DatabaseCollection
public class BansDbCollection extends ADatabaseCollection<Ban,LoginServerIDFactory> {
    private static final Logger log= LoggerFactory.getLogger(BansDbCollection.class);

    private BansDbCollection(final Class<Ban> clazz) {
        super(clazz, "bans");
        this.addLockInfo(new DatabaseLockInfo(LSIDStorageType.BAN, "",""));
    }

    public void cleanUp() {
        try {
            final BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("","");
            final WriteResult remove = this.collection.remove(basicDBObject);
            if (remove.getN() > 0) {
                BansDbCollection.log.info("clean up count :", remove.getN());
            }
        }
        catch (MongoException ex) {
            BansDbCollection.log.error("cleanUp Error", (Throwable)ex);
        }
    }

    public List<Ban> loadBans(final int n, final EBanCriteriaType eBanCriteriaType, final String s) {
        final ArrayList<Ban> list = new ArrayList<Ban>();
        try {
            final BasicDBObject basicDBObject2 = new BasicDBObject();
            basicDBObject2.append("","");
            if (eBanCriteriaType != null) {
                basicDBObject2.append(eBanCriteriaType.getFieldName(), (Object)s);
            }
            if (n > 0) {
                basicDBObject2.append("", n);
            }
            this.collection.find(basicDBObject2).forEach(basicDBObject -> list.add(new Ban((BasicDBObject) basicDBObject)));
            return list;
        }
        catch (MongoException ex) {
            BansDbCollection.log.error("load bans Error", ex);
            return list;
        }
    }

    public static BansDbCollection getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final BansDbCollection INSTANCE = new BansDbCollection(Ban.class);
    }
}
