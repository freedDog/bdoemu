package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName IAcceptConditionHandler
 * @Description  接受条件处理程序
 * @Author JiangBangMing
 * @Date 2019/7/8 21:22
 * VERSION 1.0
 */

public interface IAcceptConditionHandler {

    void load(final String p0, final String p1, final String p2, final boolean p3);

    boolean checkCondition(final Player p0);
}
