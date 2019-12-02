package com.bdoemu.gameserver.model.skills.packageeffects.enums;

import com.bdoemu.gameserver.model.skills.packageeffects.AChargeUserEffect;
import com.bdoemu.gameserver.model.skills.packageeffects.ArmstrongsSkillAddonPackageEffect;
import com.bdoemu.gameserver.model.skills.packageeffects.ArmstrongsSkillPackageEffect;
import com.bdoemu.gameserver.model.skills.packageeffects.CustomizationPackageEffect;
import com.bdoemu.gameserver.model.skills.packageeffects.KamasilvesBlessingPackageEffect;
import com.bdoemu.gameserver.model.skills.packageeffects.MervsPalettePackageEffect;
import com.bdoemu.gameserver.model.skills.packageeffects.PearlPackageEffect;
import com.bdoemu.gameserver.model.skills.packageeffects.PremiumPackageEffect;
import com.bdoemu.gameserver.model.skills.packageeffects.StarterPackageEffect;

/**
 * @ClassName EChargeUserType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 14:53
 * VERSION 1.0
 */
public enum EChargeUserType {
    StarterPackage {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return new StarterPackageEffect(startTime, this);
        }
    },
    PremiumPackage {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return new PremiumPackageEffect(startTime, this);
        }
    },
    PearlPackage {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return new PearlPackageEffect(startTime, this);
        }
    },
    PcRoom {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return null;
        }
    },
    CustomizationPackage {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return new CustomizationPackageEffect(startTime, this);
        }
    },
    DyeingPackage {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return new MervsPalettePackageEffect(startTime, this);
        }
    },
    Kamasilve {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return new KamasilvesBlessingPackageEffect(startTime, this);
        }
    },
    UnlimitedSkillChange {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return new ArmstrongsSkillAddonPackageEffect(startTime, this);
        }
    },
    UnlimitedSkillAwakening {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return new ArmstrongsSkillPackageEffect(startTime, this);
        }
    },
    RussiaPack3 {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return null;
        }
    },
    BlackSpiritTraining {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return null;
        }
    },
    PcRoomUserHomeBuff {
        @Override
        AChargeUserEffect newInstance(final long startTime) {
            return null;
        }
    };

    abstract AChargeUserEffect newInstance(final long p0);

    public AChargeUserEffect newChargeUserEffectInstance(final long startTime) {
        return this.newInstance(startTime);
    }
}
