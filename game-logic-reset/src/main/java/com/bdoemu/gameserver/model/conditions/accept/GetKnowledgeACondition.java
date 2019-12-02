package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName GetKnowledgeACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:25
 * VERSION 1.0
 */

public class GetKnowledgeACondition implements IAcceptConditionHandler{
    private boolean hasExclamation;
    private int cardId;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.cardId = Integer.parseInt(conditionValue);
        this.hasExclamation = hasExclamation;
    }

    @Override
    public boolean checkCondition(final Player player) {
        final boolean result = player.getMentalCardHandler().containsCard(this.cardId);
        return this.hasExclamation != result;
    }
}
