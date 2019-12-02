package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName EWeaponType
 * @Description  武器类型
 * @Author JiangBangMing
 * @Date 2019/7/6 14:34
 * VERSION 1.0
 */
public enum EWeaponType {

    /**无*/
    None,
    /**武器*/
    Weapon,
    /**觉醒武器*/
    AwakeningWeapon;

    public boolean isNone() {
        return this == EWeaponType.None;
    }

    public boolean isWeapon() {
        return this == EWeaponType.Weapon;
    }

    public boolean isAwakeningWeapon() {
        return this == EWeaponType.AwakeningWeapon;
    }
}
