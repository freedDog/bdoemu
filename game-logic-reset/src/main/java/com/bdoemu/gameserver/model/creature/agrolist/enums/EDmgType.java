package com.bdoemu.gameserver.model.creature.agrolist.enums;

/**
 * @ClassName EDmgType
 * @Description  伤害类型
 * @Author JiangBangMing
 * @Date 2019/7/11 11:13
 * VERSION 1.0
 */
public enum EDmgType {
    /**默认*/
    Default(0),
    /**危险*/
    Critical(1),
    /**块*/
    Block(2),
    /**守卫*/
    Guard(3),
    Unk(4),
    /**免疫*/
    Immuned(5),
    /**闪避*/
    Evasion(6);

    private int id;

    EDmgType(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
