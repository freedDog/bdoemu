package com.bdoemu.gameserver.model.functions;

import com.bdoemu.gameserver.model.creature.npc.Npc;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.templates.CreatureTemplate;

/**
 * @ClassName IFunctionHandler
 * @Description  功能处理
 * @Author JiangBangMing
 * @Date 2019/7/9 15:24
 * VERSION 1.0
 */

public interface IFunctionHandler {

    void load(final int p0, final String p1);

    void doFunction(final Player p0, final Npc p1, final long p2, final CreatureTemplate p3, final int p4);

    boolean isDisabledByContentsGroup();
}
