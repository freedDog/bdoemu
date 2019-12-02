package com.bdoemu.gameserver.model.skills.packageeffects;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.skills.packageeffects.enums.EChargeUserType;

/**
 * @ClassName PremiumPackageEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 10:09
 * VERSION 1.0
 */

public class PremiumPackageEffect extends AChargeUserEffect{

    public PremiumPackageEffect(final long effectEndTime, final EChargeUserType chargeUserType) {
        super(effectEndTime, chargeUserType);
    }

    @Override
    public void initEffect(final Player owner) {
//        owner.sendPacket(new SMInventorySlotCount(owner.getPlayerBag().getInventory().getExpandSize()));
    }

    @Override
    public void endEffect(final Player owner) {
//        owner.sendPacket(new SMInventorySlotCount(owner.getPlayerBag().getInventory().getExpandSize()));
    }
}
