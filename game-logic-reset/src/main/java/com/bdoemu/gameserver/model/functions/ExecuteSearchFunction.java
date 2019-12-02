package com.bdoemu.gameserver.model.functions;

import com.bdoemu.gameserver.model.creature.npc.Npc;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.templates.CreatureTemplate;

/**
 * @ClassName ExecuteSearchFunction
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:09
 * VERSION 1.0
 */

public class ExecuteSearchFunction implements IFunctionHandler{

    @Override
    public void load(final int dialogIndex, final String functionData) {
    }

    @Override
    public boolean isDisabledByContentsGroup() {
        return false;
    }

    @Override
    public void doFunction(final Player player, final Npc npc, final long applyCount, final CreatureTemplate creatureTemplate, final int dialogIndex) {
        player.getPlayerQuestHandler().setSearchQuestCharacterId(creatureTemplate.getCreatureId());
//        player.sendPacket(new SMQuestSearchStart());
    }
}
