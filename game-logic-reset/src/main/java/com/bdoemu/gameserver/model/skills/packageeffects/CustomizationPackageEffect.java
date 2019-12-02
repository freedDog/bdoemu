package com.bdoemu.gameserver.model.skills.packageeffects;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.skills.packageeffects.enums.EChargeUserType;

/**
 * @ClassName CustomizationPackageEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 10:13
 * VERSION 1.0
 */

public class CustomizationPackageEffect extends AChargeUserEffect{

    public CustomizationPackageEffect(final long effectEndTime, final EChargeUserType chargeUserType) {
        super(effectEndTime, chargeUserType);
    }

    @Override
    public void initEffect(final Player owner) {
//        owner.sendPacket(new SMUpdateCustomizationFreeBuff());
    }

    @Override
    public void endEffect(final Player owner) {
//        owner.sendPacket(new SMUpdateCustomizationFreeBuff());
    }
}
