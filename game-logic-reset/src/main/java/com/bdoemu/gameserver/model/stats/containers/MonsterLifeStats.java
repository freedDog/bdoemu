package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.model.creature.monster.Monster;

/**
 * @ClassName MonsterLifeStats
 * @Description  怪物生命状态
 * @Author JiangBangMing
 * @Date 2019/7/7 20:10
 * VERSION 1.0
 */

public class MonsterLifeStats extends LifeStats<Monster>{
    public MonsterLifeStats(final Monster owner) {
        super(owner, owner.getGameStats().getHp().getIntMaxValue(), owner.getGameStats().getMp().getIntMaxValue());
    }
}
