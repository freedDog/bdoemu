package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName ClearQuestACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:26
 * VERSION 1.0
 */

public class ClearQuestACondition implements IAcceptConditionHandler{
    private boolean hasExclamation;
    private int groupId;
    private int questId;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        final String[] values = conditionValue.split(",");
        this.groupId = Integer.parseInt(values[0].trim());
        this.questId = Integer.parseInt(values[1].trim());
        this.hasExclamation = hasExclamation;
    }

    @Override
    public boolean checkCondition(final Player player) {
        return this.hasExclamation != player.getPlayerQuestHandler().isClearedQuest(this.groupId, this.questId);
    }
}
