package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName CollectionStat
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/9 20:23
 * VERSION 1.0
 */

public class CollectionStat extends BaseRateStat{

    public CollectionStat(final Creature owner) {
        super(owner);
    }

    public int getCollectionRate() {
        return this.getRateTemplate().getCollectionPercent();
    }
}
