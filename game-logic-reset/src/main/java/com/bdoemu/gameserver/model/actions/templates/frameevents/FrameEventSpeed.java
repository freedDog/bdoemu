package com.bdoemu.gameserver.model.actions.templates.frameevents;

import com.bdoemu.commons.io.FileBinaryReader;
import com.bdoemu.gameserver.model.actions.IAction;
import com.bdoemu.gameserver.model.actions.enums.EFrameEventType;
import com.bdoemu.gameserver.model.creature.Creature;

import java.awt.*;

/**
 * @ClassName FrameEventSpeed
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 11:37
 * VERSION 1.0
 */

public class FrameEventSpeed extends FrameEvent {
    private float speed;
    private float targetSpeed;

    public FrameEventSpeed(final EFrameEventType frameEventType) {
        super(frameEventType);
    }

    @Override
    public void read(final FileBinaryReader reader) {
        super.read(reader);
        this.speed = reader.readF();
        this.targetSpeed = reader.readF();
    }

    @Override
    public boolean doFrame(final IAction action, final Creature target) {
        action.getOwner().getActionStorage().setMoveSpeed(this.speed);
        return super.doFrame(action, target);
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getTargetSpeed() {
        return this.targetSpeed;
    }
}
