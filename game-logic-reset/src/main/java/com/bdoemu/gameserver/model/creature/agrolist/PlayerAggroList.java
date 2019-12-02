package com.bdoemu.gameserver.model.creature.agrolist;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.player.Player;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName PlayerAggroList
 * @Description  玩家仇恨列表
 * @Author JiangBangMing
 * @Date 2019/7/9 20:36
 * VERSION 1.0
 */

public class PlayerAggroList extends CreatureAggroList{

    private Creature target;

    public PlayerAggroList(final Player actor) {
        super(actor);
    }

    @Override
    public void addDmg(final Creature creature, final double dmg) {
    }

    @Override
    public Creature getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(final Creature target) {
        this.target = target;
    }

    @Override
    public Creature getMostDamageCreature() {
        return this.target;
    }

    @Override
    public List<Creature> getAggroCreatures() {
        return Collections.singletonList(this.target);
    }
}
