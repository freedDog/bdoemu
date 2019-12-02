package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName AttackSpeedStat
 * @Description  攻击速度
 * @Author JiangBangMing
 * @Date 2019/7/6 11:33
 * VERSION 1.0
 */

public class AttackSpeedStat extends BaseRateStat {

    public AttackSpeedStat(final Creature owner) {
        super(owner);
    }

    public int getAttackSpeedRate() {
        return this.getRateTemplate().getAttackspeedPercent() + this.currentStatRate;
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
