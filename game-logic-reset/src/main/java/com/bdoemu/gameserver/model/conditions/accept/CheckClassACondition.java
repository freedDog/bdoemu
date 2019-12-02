package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName CheckClassACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:23
 * VERSION 1.0
 */

public class CheckClassACondition implements IAcceptConditionHandler{

    private int classId;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.classId = Integer.parseInt(conditionValue);
    }

    @Override
    public boolean checkCondition(final Player player) {
        return player.getClassType().getId() == this.classId;
    }
}
