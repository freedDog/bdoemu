package com.bdoemu.gameserver.databaseCollections;

import com.bdoemu.commons.database.mongo.ADatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseLockInfo;
import com.bdoemu.core.idFactory.GSIDStorageType;
import com.bdoemu.core.idFactory.GameServerIDFactory;
import com.bdoemu.gameserver.model.items.MasterItemMarket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName MasterItemMarketDBCollection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 10:57
 * VERSION 1.0
 */

@DatabaseCollection
public class MasterItemMarketDBCollection extends ADatabaseCollection<MasterItemMarket, GameServerIDFactory> {

    private static final Logger log = LoggerFactory.getLogger(MasterItemMarketDBCollection.class);

    private MasterItemMarketDBCollection(final Class<MasterItemMarket> clazz) {
        super(clazz, "masterItemMarket");
        addLockInfo(new DatabaseLockInfo(GSIDStorageType.MasterItemMarket, "_id"));
    }

    public static MasterItemMarketDBCollection getInstance() {
        return MasterItemMarketDBCollection.Holder.INSTANCE;
    }

    private static class Holder {
        static final MasterItemMarketDBCollection INSTANCE = new MasterItemMarketDBCollection(MasterItemMarket.class);
    }
}
