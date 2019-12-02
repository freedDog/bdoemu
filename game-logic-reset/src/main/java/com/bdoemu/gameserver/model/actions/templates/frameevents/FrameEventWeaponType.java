package com.bdoemu.gameserver.model.actions.templates.frameevents;

import com.bdoemu.commons.io.FileBinaryReader;
import com.bdoemu.gameserver.model.actions.IAction;
import com.bdoemu.gameserver.model.actions.enums.EFrameEventType;
import com.bdoemu.gameserver.model.actions.enums.EWeaponType;
import com.bdoemu.gameserver.model.creature.Creature;

import java.util.Collection;

/**
 * @ClassName FrameEventWeaponType
 * @Description 武器类型
 * @Author JiangBangMing
 * @Date 2019/7/11 14:34
 * VERSION 1.0
 */

public class FrameEventWeaponType extends FrameEvent{
    private EWeaponType weaponType;

    public FrameEventWeaponType(final EFrameEventType frameEventType) {
        super(frameEventType);
    }

    @Override
    public void read(final FileBinaryReader reader) {
        super.read(reader);
        this.weaponType = EWeaponType.values()[reader.readD()];
    }

    @Override
    public boolean doFrame(final IAction action, final int npcGameObjId, final long staticObjectId, final Collection<Creature> targets, final Collection<Creature> playerTargets) {
        action.getOwner().getActionStorage().setWeaponType(this.weaponType);
        return true;
    }
}
