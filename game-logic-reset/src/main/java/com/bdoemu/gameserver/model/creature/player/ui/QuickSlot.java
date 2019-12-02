package com.bdoemu.gameserver.model.creature.player.ui;

import com.bdoemu.commons.database.mongo.JSONable;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName QuickSlot
 * @Description  快捷槽
 * @Author JiangBangMing
 * @Date 2019/7/9 18:19
 * VERSION 1.0
 */

public class QuickSlot extends JSONable {

    private String quickSlotData;

    public QuickSlot(final BasicDBObject dbObj) {
        this.quickSlotData = dbObj.getString("quickSlotData");
    }

    public QuickSlot() {
        this.quickSlotData = "";
    }

    public String getQuickSlotData() {
        return this.quickSlotData;
    }

    public void update(final String data) {
        this.quickSlotData = data;
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("quickSlotData", this.quickSlotData);
        return builder.get();
    }
}
