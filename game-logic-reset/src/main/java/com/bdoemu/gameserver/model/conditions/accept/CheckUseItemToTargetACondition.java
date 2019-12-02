package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName CheckUseItemToTargetACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 16:02
 * VERSION 1.0
 */

public class CheckUseItemToTargetACondition implements IAcceptConditionHandler{

    private int useItemTargetType;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.useItemTargetType = Integer.parseInt(conditionValue);
    }

    @Override
    public boolean checkCondition(final Player player) {
        return true;
    }
}
