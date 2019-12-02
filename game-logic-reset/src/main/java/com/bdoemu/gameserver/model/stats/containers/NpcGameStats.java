package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.model.creature.npc.Npc;

/**
 * @ClassName NpcGameStats
 * @Description  NPC 游戏状态
 * @Author JiangBangMing
 * @Date 2019/7/9 15:18
 * VERSION 1.0
 */

public class NpcGameStats extends GameStats<Npc> {

    public NpcGameStats(final Npc owner) {
        super(owner);
    }
}
