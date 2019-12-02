package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.enums.EEquipSlot;

/**
 * @ClassName CheckEquipSlotAndCashCondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:28
 * VERSION 1.0
 */

public class CheckEquipSlotAndCashCondition implements IAcceptConditionHandler{

    private EEquipSlot equipSlot;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.equipSlot = EEquipSlot.valueof(conditionValue);
    }

    @Override
    public boolean checkCondition(final Player player) {
        return player.getEquipments().containsItem(this.equipSlot.getId());
    }
}
