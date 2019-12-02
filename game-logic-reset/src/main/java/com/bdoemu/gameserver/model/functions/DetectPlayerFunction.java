package com.bdoemu.gameserver.model.functions;

import com.bdoemu.commons.model.enums.EStringTable;
import com.bdoemu.gameserver.model.creature.npc.Npc;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.templates.CreatureTemplate;

/**
 * @ClassName DetectPlayerFunction
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 12:09
 * VERSION 1.0
 */

public class DetectPlayerFunction implements IFunctionHandler{
    private int wp;

    @Override
    public void load(final int dialogIndex, final String functionData) {
        this.wp = Integer.parseInt(functionData.toLowerCase().replace("wp", "").trim());
    }

    @Override
    public boolean isDisabledByContentsGroup() {
        return false;
    }

    @Override
    public void doFunction(final Player player, final Npc npc, final long applyCount, final CreatureTemplate creatureTemplate, final int dialogIndex) {
        if (player.addWp(-this.wp)) {
//            player.sendPacket(new SMOpenDetectPlayer());
        } else {
//            player.sendPacket(new SMNak(EStringTable.eErrNoMentalNotEnoughWp, CMDetectCharacter.class));
        }
    }
}
