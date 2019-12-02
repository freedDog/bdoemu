package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.lifeExperience.enums.ELifeExpType;

/**
 * @ClassName GetLifeLevelACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:22
 * VERSION 1.0
 */

public class GetLifeLevelACondition implements IAcceptConditionHandler{
    private String operator;
    private int value;
    private ELifeExpType type;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.type = ELifeExpType.valueOf(Integer.parseInt(conditionValue));
        this.value = Integer.parseInt(operatorValue.trim());
        this.operator = operator;
    }

    @Override
    public boolean checkCondition(final Player player) {
        final String operator = this.operator;
        switch (operator) {
            case "<": {
                return player.getLifeExperienceStorage().getLifeExperience(this.type).getLevel() < this.value;
            }
            case ">": {
                return player.getLifeExperienceStorage().getLifeExperience(this.type).getLevel() > this.value;
            }
            case "=": {
                return player.getLifeExperienceStorage().getLifeExperience(this.type).getLevel() == this.value;
            }
            default: {
                return false;
            }
        }
    }
}
