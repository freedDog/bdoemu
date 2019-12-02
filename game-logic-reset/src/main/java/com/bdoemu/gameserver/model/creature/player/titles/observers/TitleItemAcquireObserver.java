package com.bdoemu.gameserver.model.creature.player.titles.observers;

import com.bdoemu.gameserver.model.creature.player.titles.templates.TitleTemplate;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName TitleItemAcquireObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:53
 * VERSION 1.0
 */

public class TitleItemAcquireObserver extends ATitleObserver{

    private int itemCount;

    @Override
    public void load(final BasicDBObject dbObject, final TitleTemplate template) {
        super.load(template);
        this.itemCount = dbObject.getInt("itemCount", 0);
    }

    @Override
    public boolean canReward(final Object... params) {
        if ((int) params[0] == this.template.getParameter1() && this.template.getParameter2() < this.itemCount) {
            final int addedCount = (int) params[1];
            this.itemCount += addedCount;
            return this.itemCount >= this.template.getParameter2();
        }
        return false;
    }

    @Override
    public Object getKey() {
        return this.template.getParameter1();
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("titleId", this.template.getTitleId());
        builder.append("itemCount", this.itemCount);
        return builder.get();
    }
}
