package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName CheckTerritoryACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 15:52
 * VERSION 1.0
 */

public class CheckTerritoryACondition implements IAcceptConditionHandler{

    private int[] territories;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        final String[] values = conditionValue.split(",");
        this.territories = new int[values.length];
        for (int index = 0; index < values.length; ++index) {
            this.territories[index] = Integer.parseInt(values[index].trim());
        }
    }

    @Override
    public boolean checkCondition(final Player player) {
        return true;
    }
}
