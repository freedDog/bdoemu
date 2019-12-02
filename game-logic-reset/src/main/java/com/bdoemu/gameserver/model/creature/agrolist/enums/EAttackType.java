package com.bdoemu.gameserver.model.creature.agrolist.enums;

/**
 * @ClassName EAttackType
 * @Description  攻击类型
 * @Author JiangBangMing
 * @Date 2019/7/11 11:08
 * VERSION 1.0
 */
public enum EAttackType {
    /**背后攻击*/
    BackAttack(0),
    /**反击*/
    CounterAttack(1),
    /**向下攻击*/
    DownAttack(2),
    /**快速攻击*/
    SpeedAttack(3),
    /**空中攻击*/
    AirAttack(4),
    /**正面攻击*/
    FrontAttack(5);

    private byte id;

    EAttackType(final int id) {
        this.id = (byte) id;
    }

    public byte getId() {
        return this.id;
    }

    public boolean isBackAttack() {
        return this == EAttackType.BackAttack;
    }

}
