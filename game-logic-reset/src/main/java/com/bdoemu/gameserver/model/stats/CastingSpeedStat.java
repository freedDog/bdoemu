package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName CastingSpeedStat
 * @Description  施法速度
 * @Author JiangBangMing
 * @Date 2019/7/6 11:39
 * VERSION 1.0
 */

public class CastingSpeedStat extends BaseRateStat{

    public CastingSpeedStat(final Creature owner) {
        super(owner);
    }

    public int getCastingSpeedRate() {
        return this.getRateTemplate().getCastingspeedPercent() + this.currentStatRate;
    }

    @Override
    protected float getValueBonus() {
        return 5.0f;
    }

    @Override
    protected float getMaxValueBonus() {
        return 5.0f;
    }
}
