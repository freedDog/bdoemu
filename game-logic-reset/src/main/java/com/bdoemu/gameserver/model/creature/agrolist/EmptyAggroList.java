package com.bdoemu.gameserver.model.creature.agrolist;

import com.bdoemu.gameserver.model.creature.Creature;

import java.util.List;

/**
 * @ClassName EmptyAggroList
 * @Description  空仇恨列表
 * @Author JiangBangMing
 * @Date 2019/7/9 15:53
 * VERSION 1.0
 */

public class EmptyAggroList implements IAggroList{
    @Override
    public void addCreature(Creature p0) {

    }

    @Override
    public void addDmg(Creature p0, double p1) {

    }

    @Override
    public void clear(boolean p0) {

    }

    @Override
    public Creature getMostDamageCreature() {
        return null;
    }

    @Override
    public Creature getTarget() {
        return null;
    }

    @Override
    public void setTarget(Creature p0) {

    }

    @Override
    public int getTargetGameObjId() {
        return 0;
    }

    @Override
    public AggroInfo getMostDamage() {
        return null;
    }

    @Override
    public CreatureAggroInfo getMostHate() {
        return null;
    }

    @Override
    public List<Creature> getAggroCreatures() {
        return null;
    }

    @Override
    public CreatureAggroInfo getAggroInfo(Creature p0) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
