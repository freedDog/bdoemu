package com.bdoemu.gameserver.model.conditions.complete;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.observers.enums.EObserveType;
import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName LearnSkillCCondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 16:06
 * VERSION 1.0
 */

public class LearnSkillCCondition implements ICompleteConditionHandler{

    private int skillId;

    @Override
    public void load(final String... function) {
        this.skillId = Integer.parseInt(function[0]);
    }

    @Override
    public int checkCondition(final Object... params) {
        return ((int) params[0] == this.skillId) ? 1 : 0;
    }

    @Override
    public int getStepCount() {
        return 1;
    }

    @Override
    public EObserveType getObserveType() {
        return EObserveType.learnSkill;
    }

    @Override
    public boolean checkStep(final Player player) {
        return player.getSkillList().containsSkill(this.skillId);
    }

    @Override
    public Object getKey() {
        return this.skillId;
    }

    @Override
    public boolean canInteractForQuest(final Creature target) {
        return false;
    }
}
