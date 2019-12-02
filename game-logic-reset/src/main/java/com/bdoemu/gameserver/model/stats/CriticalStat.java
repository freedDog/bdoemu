package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName CriticalStat
 * @Description  鉴定状态
 * @Author JiangBangMing
 * @Date 2019/7/9 20:21
 * VERSION 1.0
 */

public class CriticalStat extends BaseRateStat{

    public CriticalStat(final Creature owner) {
        super(owner);
    }

    public int getCriticalRate() {
        return this.getRateTemplate().getCriticalPercent();
    }
}
