package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName GetLevelACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:24
 * VERSION 1.0
 */

public class GetLevelACondition implements IAcceptConditionHandler{

    private int level;
    private String operator;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.level = Integer.parseInt(operatorValue.trim());
        this.operator = operator;
    }

    @Override
    public boolean checkCondition(final Player player) {
        final String operator = this.operator;
        switch (operator) {
            case "<": {
                return player.getLevel() < this.level;
            }
            case ">": {
                return player.getLevel() > this.level;
            }
            case "=": {
                return player.getLevel() == this.level;
            }
            default: {
                return false;
            }
        }
    }
}
