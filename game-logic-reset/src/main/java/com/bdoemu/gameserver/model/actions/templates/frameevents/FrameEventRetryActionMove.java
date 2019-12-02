package com.bdoemu.gameserver.model.actions.templates.frameevents;

import com.bdoemu.gameserver.model.actions.IAction;
import com.bdoemu.gameserver.model.actions.enums.EFrameEventType;
import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName FrameEventRetryActionMove
 * @Description 重试操作移动
 * @Author JiangBangMing
 * @Date 2019/7/11 14:33
 * VERSION 1.0
 */

public class FrameEventRetryActionMove extends FrameEvent{

    public FrameEventRetryActionMove(final EFrameEventType frameEventType) {
        super(frameEventType);
    }

    @Override
    public boolean doFrame(final IAction action, final Creature target) {
//        action.getOwner().sendBroadcastItSelfPacket(new SMActionRefresh(action));
        return super.doFrame(action, target);
    }
}
