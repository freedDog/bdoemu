package com.bdoemu.gameserver.model.creature.player.titles.observers;

import com.mongodb.DBObject;

/**
 * @ClassName TitleExploreNodeObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:55
 * VERSION 1.0
 */

public class TitleExploreNodeObserver extends ATitleObserver{
    @Override
    public boolean canReward(final Object... params) {
        return (int) params[1] == this.template.getParameter2();
    }

    @Override
    public Object getKey() {
        return this.template.getParameter1();
    }

    @Override
    public DBObject toDBObject() {
        return null;
    }
}
