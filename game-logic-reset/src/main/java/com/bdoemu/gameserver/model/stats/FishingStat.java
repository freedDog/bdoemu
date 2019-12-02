package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName FishingStat
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/9 20:22
 * VERSION 1.0
 */

public class FishingStat extends BaseRateStat{

    public FishingStat(final Creature owner) {
        super(owner);
    }

    public int getFishingRate() {
        return this.getRateTemplate().getFishingPercent();
    }
}
