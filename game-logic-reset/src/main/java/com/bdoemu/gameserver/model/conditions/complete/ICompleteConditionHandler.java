package com.bdoemu.gameserver.model.conditions.complete;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.observers.enums.EObserveType;
import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName ICompleteConditionHandler
 * @Description   完成条件处理程序
 * @Author JiangBangMing
 * @Date 2019/7/8 21:27
 * VERSION 1.0
 */

public interface ICompleteConditionHandler {

    void load(final String... p0);

    int checkCondition(final Object... p0);

    int getStepCount();

    EObserveType getObserveType();

    boolean checkStep(final Player p0);

    Object getKey();

    boolean canInteractForQuest(final Creature p0);
}
