package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName CheckRegionTypeACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 16:01
 * VERSION 1.0
 */

public class CheckRegionTypeACondition implements IAcceptConditionHandler{

    private int regionType;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.regionType = Integer.parseInt(conditionValue);
    }

    @Override
    public boolean checkCondition(final Player player) {
        return this.regionType == 0 && player.getRegion().getTemplate().isDesert();
    }
}
