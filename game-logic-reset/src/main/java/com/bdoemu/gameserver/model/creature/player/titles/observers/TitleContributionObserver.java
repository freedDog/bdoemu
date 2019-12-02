package com.bdoemu.gameserver.model.creature.player.titles.observers;

import com.bdoemu.gameserver.model.creature.player.titles.templates.TitleTemplate;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName TitleContributionObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:57
 * VERSION 1.0
 */

public class TitleContributionObserver extends ATitleObserver{

    private int contributionPoints;

    @Override
    public void load(final BasicDBObject dbObject, final TitleTemplate template) {
        super.load(template);
        this.contributionPoints = dbObject.getInt("contributionPoints");
    }

    @Override
    public boolean canReward(final Object... params) {
        this.contributionPoints = (int) params[0];
        return this.contributionPoints >= this.template.getParameter1();
    }

    @Override
    public Object getKey() {
        return this.template.getParameter1();
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("titleId", this.template.getTitleId());
        builder.append("contributionPoints", this.contributionPoints);
        return builder.get();
    }
}
