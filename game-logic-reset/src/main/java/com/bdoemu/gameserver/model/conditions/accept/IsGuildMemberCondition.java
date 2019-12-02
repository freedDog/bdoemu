package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName IsGuildMemberCondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:26
 * VERSION 1.0
 */

public class IsGuildMemberCondition implements IAcceptConditionHandler{
    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
    }

    @Override
    public boolean checkCondition(final Player player) {
        return player.hasGuild();
    }
}
