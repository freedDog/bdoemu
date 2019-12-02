package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName MoveSpeedStat
 * @Description  移动速度
 * @Author JiangBangMing
 * @Date 2019/7/6 11:22
 * VERSION 1.0
 */

public class MoveSpeedStat extends BaseRateStat{

    public MoveSpeedStat(final Creature owner) {
        super(owner);
    }

    public int getMoveSpeedRate() {
        return this.getRateTemplate().getMovespeedPercent() + this.currentStatRate;
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
