package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName CheckPartyACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 15:56
 * VERSION 1.0
 */

public class CheckPartyACondition implements IAcceptConditionHandler{
    private boolean hasExclamation;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.hasExclamation = hasExclamation;
    }

    @Override
    public boolean checkCondition(final Player player) {
        final boolean result = player.hasParty();
        return this.hasExclamation != result;
    }
}
