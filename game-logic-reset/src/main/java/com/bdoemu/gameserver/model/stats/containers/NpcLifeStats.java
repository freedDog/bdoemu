package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.model.creature.npc.Npc;

/**
 * @ClassName NpcLifeStats
 * @Description  npc 生命状态
 * @Author JiangBangMing
 * @Date 2019/7/9 15:18
 * VERSION 1.0
 */

public class NpcLifeStats extends LifeStats<Npc>{

    public NpcLifeStats(final Npc owner) {
        super(owner, owner.getGameStats().getHp().getIntMaxValue(), owner.getGameStats().getMp().getIntMaxValue());
    }
}
