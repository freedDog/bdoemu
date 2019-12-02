package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName CheckWomanACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:23
 * VERSION 1.0
 */

public class CheckWomanACondition implements IAcceptConditionHandler{

    private boolean hasExclamation;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.hasExclamation = hasExclamation;
    }

    @Override
    public boolean checkCondition(final Player player) {
        return this.hasExclamation != player.getClassType().getGenderType().isWoman();
    }
}
