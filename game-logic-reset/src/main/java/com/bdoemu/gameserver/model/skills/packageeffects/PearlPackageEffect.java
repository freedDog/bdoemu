package com.bdoemu.gameserver.model.skills.packageeffects;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.skills.packageeffects.enums.EChargeUserType;

/**
 * @ClassName PearlPackageEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 10:11
 * VERSION 1.0
 */

public class PearlPackageEffect extends AChargeUserEffect{

    public PearlPackageEffect(final long effectEndTime, final EChargeUserType chargeUserType) {
        super(effectEndTime, chargeUserType);
    }

    @Override
    public void initEffect(final Player owner) {
    }

    @Override
    public void endEffect(final Player owner) {
    }
}
