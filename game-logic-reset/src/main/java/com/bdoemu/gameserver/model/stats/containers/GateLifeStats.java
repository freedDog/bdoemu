package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.model.creature.Gate;

/**
 * @ClassName GateLifeStats
 * @Description  门生命状态
 * @Author JiangBangMing
 * @Date 2019/7/9 15:56
 * VERSION 1.0
 */

public class GateLifeStats extends LifeStats<Gate> {

    public GateLifeStats(final Gate owner) {
        super(owner, owner.getGameStats().getHp().getIntMaxValue(), owner.getGameStats().getMp().getIntMaxValue());
    }
}
