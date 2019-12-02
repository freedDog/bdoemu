package com.bdoemu.gameserver.model.items;

import com.bdoemu.commons.database.mongo.JSONable;
import com.bdoemu.core.idFactory.GSIDStorageType;
import com.bdoemu.core.idFactory.GameServerIDFactory;
import com.bdoemu.gameserver.model.world.enums.ETerritoryKeyType;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName ReservationItemMarket
 * @Description 预定道具商品
 * @Author JiangBangMing
 * @Date 2019/7/11 10:53
 * VERSION 1.0
 */

public class ReservationItemMarket extends JSONable {

    private final int itemId;
    private final int enchantLevel;
    private final ETerritoryKeyType territoryKeyType;
    private final long money;
    private final long count;
    private final long accountId;
    private final long objectId;

    public ReservationItemMarket(final int itemId, final int enchantLevel, final ETerritoryKeyType territoryKeyType, final long money, final long count, final long accountId) {
        this.itemId = itemId;
        this.enchantLevel = enchantLevel;
        this.territoryKeyType = territoryKeyType;
        this.money = money;
        this.count = count;
        this.accountId = accountId;
        this.objectId = GameServerIDFactory.getInstance().nextId(GSIDStorageType.ItemMarket);
    }

    public ReservationItemMarket(final BasicDBObject basicDBObject) {
        this.objectId = basicDBObject.getLong("_id");
        this.itemId = basicDBObject.getInt("itemId");
        this.enchantLevel = basicDBObject.getInt("enchantLevel");
        this.territoryKeyType = ETerritoryKeyType.valueOf(basicDBObject.getString("territoryKeyType"));
        this.money = basicDBObject.getLong("money");
        this.count = basicDBObject.getLong("count");
        this.accountId = basicDBObject.getLong("accountId");
    }

    public long getCount() {
        return this.count;
    }

    public long getMoney() {
        return this.money;
    }

    public ETerritoryKeyType getTerritoryKeyType() {
        return this.territoryKeyType;
    }

    public int getEnchantLevel() {
        return this.enchantLevel;
    }

    public int getItemId() {
        return this.itemId;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public long getObjectId() {
        return this.objectId;
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        builder.append("_id", this.objectId);
        builder.append("itemId",  this.itemId);
        builder.append("enchantLevel",  this.enchantLevel);
        builder.append("territoryKeyType",  this.territoryKeyType.name());
        builder.append("money", this.money);
        builder.append("count",  this.count);
        builder.append("accountId",  this.accountId);
        return builder.get();
    }
}
