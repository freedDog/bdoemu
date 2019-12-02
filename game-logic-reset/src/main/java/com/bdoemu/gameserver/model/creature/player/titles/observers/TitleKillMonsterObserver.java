package com.bdoemu.gameserver.model.creature.player.titles.observers;

import com.bdoemu.gameserver.model.creature.player.titles.templates.TitleTemplate;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName TitleKillMonsterObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:38
 * VERSION 1.0
 */

public class TitleKillMonsterObserver extends ATitleObserver{
    private int killedMonsterCount;

    @Override
    public void load(final BasicDBObject dbObject, final TitleTemplate template) {
        super.load(template);
        this.killedMonsterCount = dbObject.getInt("killedMonsterCount");
    }

    @Override
    public boolean canReward(final Object... params) {
        ++this.killedMonsterCount;
        return (int) params[0] == this.template.getParameter1() && this.killedMonsterCount == this.template.getParameter2();
    }

    @Override
    public Object getKey() {
        return this.template.getParameter1();
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("titleId", this.template.getTitleId());
        builder.append("killedMonsterCount", this.killedMonsterCount);
        return builder.get();
    }
}
