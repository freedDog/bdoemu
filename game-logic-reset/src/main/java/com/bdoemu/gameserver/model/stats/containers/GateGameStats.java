package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.model.creature.Gate;

/**
 * @ClassName GateGameStats
 * @Description  门游戏状态
 * @Author JiangBangMing
 * @Date 2019/7/9 15:55
 * VERSION 1.0
 */

public class GateGameStats extends GameStats<Gate> {

    public GateGameStats(final Gate owner) {
        super(owner);
    }
}
