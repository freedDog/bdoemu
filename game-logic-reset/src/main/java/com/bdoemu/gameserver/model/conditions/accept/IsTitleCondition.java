package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName IsTitleCondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:27
 * VERSION 1.0
 */

public class IsTitleCondition implements IAcceptConditionHandler{
    private int titleId;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.titleId = Integer.parseInt(conditionValue);
    }

    @Override
    public boolean checkCondition(final Player player) {
        return player.getTitleHandler().getTitleId() == this.titleId;
    }
}
