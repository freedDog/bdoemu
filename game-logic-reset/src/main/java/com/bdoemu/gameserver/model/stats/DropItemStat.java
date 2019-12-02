package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName DropItemStat
 * @Description  道具掉落率
 * @Author JiangBangMing
 * @Date 2019/7/6 11:41
 * VERSION 1.0
 */

public class DropItemStat extends BaseRateStat{

    public DropItemStat(final Creature owner) {
        super(owner);
    }

    public int getDropItemRate() {
        return this.getRateTemplate().getDropItemPercent();
    }
}
