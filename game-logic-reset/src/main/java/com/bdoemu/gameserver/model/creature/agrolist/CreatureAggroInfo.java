package com.bdoemu.gameserver.model.creature.agrolist;

import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName CreatureAggroInfo
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/6 11:52
 * VERSION 1.0
 */

public class CreatureAggroInfo extends AggroInfo{

    private final Creature creature;

    public CreatureAggroInfo(final Creature creature) {
        this.creature = creature;
    }

    public Creature getCreature() {
        return this.creature;
    }
}
