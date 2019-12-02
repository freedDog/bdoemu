package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName WeightStat
 * @Description  重力
 * @Author JiangBangMing
 * @Date 2019/7/6 11:37
 * VERSION 1.0
 */

public class WeightStat extends Stat{

    public WeightStat(final Creature owner) {
        super(owner);
    }

    public void addWeight(final long value) {
        this.value += value;
    }

    public long getWeightPercentage() {
        final long maxValue = this.getLongMaxValue();
        return (maxValue > 0L) ? (this.getLongValue() * 100L / maxValue) : maxValue;
    }
}
