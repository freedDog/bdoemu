package com.bdoemu.gameserver.model.items.enums;

/**
 * @ClassName EEnchantType
 * @Description 附魔类型
 * @Author JiangBangMing
 * @Date 2019/7/9 17:24
 * VERSION 1.0
 */

public enum EEnchantType {

    SafeEnchant,
    UnsafeEnchant,
    AccessoryEnchant,
    UnsafeRiskEnchant;

    public static EEnchantType valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            throw new IllegalArgumentException("Invalid EEnchantType id: " + reqType);
        }
        return values()[reqType];
    }

    public boolean isAccessoryEnchant() {
        return this == EEnchantType.AccessoryEnchant;
    }

    public boolean isUnsafeRiskEnchant() {
        return this == EEnchantType.UnsafeRiskEnchant;
    }
}
