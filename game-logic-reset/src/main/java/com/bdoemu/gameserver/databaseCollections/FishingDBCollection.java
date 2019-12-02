package com.bdoemu.gameserver.databaseCollections;

import com.bdoemu.commons.database.DatabaseFactory;
import com.bdoemu.gameserver.model.creature.player.fishing.FishingTopRank;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.mongodb.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName FishingDBCollection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 14:51
 * VERSION 1.0
 */

public class FishingDBCollection {
    private static final Logger log = LoggerFactory.getLogger(FishingDBCollection.class);
    private static final String COLLECTION_NAME = "fishing";

    public static void updateFishingRanking(final FishingTopRank fishingTopRank) {
        try {
            final DB db = DatabaseFactory.getInstance().getDatabase();
            final DBCollection collection = db.getCollection(COLLECTION_NAME);
            final QueryBuilder where = QueryBuilder.start("_id");
            where.is("FishingRanking");
            collection.update(where.get(), new BasicDBObject("$set", fishingTopRank.toDBObject()), true, true);
        } catch (MongoException ex) {
            FishingDBCollection.log.error("Error while update FishingTopRank", ex);
        }
    }

    public static BasicDBList loadFishingTopRanking(final int key) {
        try {
            final DB db = DatabaseFactory.getInstance().getDatabase();
            final DBCollection collection = db.getCollection("fishing");
            final BasicDBObject where = new BasicDBObject("_id", "FishingRanking");
            final BasicDBObject fields = new BasicDBObject();
            fields.put(Integer.toString(key), true);
            final BasicDBObject result = (BasicDBObject) collection.findOne(where, fields);
            if (result != null && !result.isEmpty()) {
                return (BasicDBList) result.get(Integer.toString(key));
            }
        } catch (MongoException ex) {
            FishingDBCollection.log.error("Error while load FishingTopRank", ex);
        }
        return null;
    }

    public static void init(final String name) {
        final DB db = DatabaseFactory.getInstance().getDatabase();
        final DBCollection collection = db.getCollection("fishing");
        final BasicDBObject idObject = (BasicDBObject) collection.findOne(new BasicDBObject("_id", name));
        if (idObject == null) {
            final BasicDBObject list = new BasicDBObject();
            list.put("_id", name);
            collection.insert(list);
        }
    }
}
