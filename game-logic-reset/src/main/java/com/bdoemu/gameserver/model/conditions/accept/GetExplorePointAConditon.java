package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName GetExplorePointAConditon
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 15:56
 * VERSION 1.0
 */

public class GetExplorePointAConditon implements IAcceptConditionHandler{

    private int unkNodeId;
    private int value;
    private String operator;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.unkNodeId = Integer.parseInt(conditionValue.trim());
        this.value = Integer.parseInt(operatorValue.trim());
        this.operator = operator;
    }

    @Override
    public boolean checkCondition(final Player player) {
        final String operator = this.operator;
        switch (operator) {
            case "<": {
                return player.getExplorePointHandler().getMainExplorePoint().getCurrentExplorePoints() < this.value;
            }
            case ">": {
                return player.getExplorePointHandler().getMainExplorePoint().getCurrentExplorePoints() > this.value;
            }
            case "=": {
                return player.getExplorePointHandler().getMainExplorePoint().getCurrentExplorePoints() == this.value;
            }
            default: {
                return false;
            }
        }
    }
}
