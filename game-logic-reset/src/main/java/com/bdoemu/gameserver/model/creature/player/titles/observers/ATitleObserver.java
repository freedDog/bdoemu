package com.bdoemu.gameserver.model.creature.player.titles.observers;

import com.bdoemu.commons.database.mongo.JSONable;
import com.bdoemu.gameserver.model.creature.player.titles.templates.TitleTemplate;
import com.mongodb.BasicDBObject;

/**
 * @ClassName ATitleObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 11:51
 * VERSION 1.0
 */

public abstract class ATitleObserver extends JSONable implements ITitleObserver {

    protected TitleTemplate template;

    @Override
    public void load(final TitleTemplate template) {
        this.template = template;
    }

    @Override
    public void load(final BasicDBObject dbObject, final TitleTemplate template) {
        this.template = template;
    }

    @Override
    public TitleTemplate getTemplate() {
        return this.template;
    }
    @Override
    public abstract boolean canReward(final Object... p0);

    public abstract Object getKey();
}
