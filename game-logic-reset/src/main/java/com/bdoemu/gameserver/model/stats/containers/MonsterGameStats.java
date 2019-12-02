package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.model.creature.monster.Monster;

/**
 * @ClassName MonsterGameStats
 * @Description     怪物状态
 * @Author JiangBangMing
 * @Date 2019/7/7 20:09
 * VERSION 1.0
 */

public class MonsterGameStats extends GameStats<Monster>{

    public MonsterGameStats(final Monster owner) {
        super(owner);
    }
}
