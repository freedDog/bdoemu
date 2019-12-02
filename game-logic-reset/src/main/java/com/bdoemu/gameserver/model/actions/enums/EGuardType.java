package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName EGuardType
 * @Description 保护类型
 * @Author JiangBangMing
 * @Date 2019/7/11 11:26
 * VERSION 1.0
 */

public enum  EGuardType {
    None,
    SuperArmor,
    Defence,
    DefenceAndRotate,
    Avoid,
    DamageImmune;

    public boolean isAvoid() {
        return this == EGuardType.Avoid;
    }

    public boolean isDefence() {
        return this == EGuardType.Defence;
    }
}
