package com.bdoemu.gameserver.model.actions.templates.frameevents;

import com.bdoemu.gameserver.model.actions.ATentAction;
import com.bdoemu.gameserver.model.actions.IAction;
import com.bdoemu.gameserver.model.actions.enums.EFrameEventType;
import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.enums.ECharKind;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.itemPack.events.TakeDownTentItemEvent;
import com.bdoemu.gameserver.model.houses.HouseHold;
import com.bdoemu.gameserver.model.knowlist.KnowList;

import java.util.Collection;

/**
 * @ClassName FrameEventTakeDownTent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 12:27
 * VERSION 1.0
 */

public class FrameEventTakeDownTent extends FrameEvent{

    public FrameEventTakeDownTent(final EFrameEventType frameEventType) {
        super(frameEventType);
    }

    @Override
    public boolean doFrame(final IAction action, final int npcGameObjId, final long staticObjectId, final Collection<Creature> targets, final Collection<Creature> playerTargets) {
        final ATentAction tentAction = (ATentAction) action;
        final Player player = (Player) tentAction.getOwner();
        final HouseHold tent = KnowList.getObject(player, ECharKind.Household, tentAction.getTentGameObjId());
        if (tent == null) {
            return false;
        }
        player.getPlayerBag().onEvent(new TakeDownTentItemEvent(player, tent));
        return true;
    }
}
