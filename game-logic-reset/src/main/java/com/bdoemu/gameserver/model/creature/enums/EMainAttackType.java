package com.bdoemu.gameserver.model.creature.enums;

/**
 * @ClassName EMainAttackType
 * @Description 主攻击类型
 * @Author JiangBangMing
 * @Date 2019/7/9 16:01
 * VERSION 1.0
 */

public enum  EMainAttackType {

    DDD,
    RDD,
    MDD;

    public static EMainAttackType valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            throw new IllegalArgumentException("Invalid EMainAttackType id: " + reqType);
        }
        return values()[reqType];
    }
}
