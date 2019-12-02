package com.bdoemu.gameserver.model.skills.buffs.enums;

/**
 * @ClassName BuffType
 * @Description buff 类型
 * @Author JiangBangMing
 * @Date 2019/7/6 12:02
 * VERSION 1.0
 */
public enum BuffType {
    /**buff*/
    Buff,
    /**伤害*/
    Damage;

    public boolean isDmg() {
        return this == BuffType.Damage;
    }

    public boolean isBuff() {
        return this == BuffType.Buff;
    }
}
