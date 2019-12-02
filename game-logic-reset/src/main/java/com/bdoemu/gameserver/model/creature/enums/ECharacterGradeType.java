package com.bdoemu.gameserver.model.creature.enums;

/**
 * @ClassName ECharacterGradeType
 * @Description  角色等级类型
 * @Author JiangBangMing
 * @Date 2019/7/9 16:24
 * VERSION 1.0
 */
public enum ECharacterGradeType {
    /**正常*/
    Normal(0),
    /**精英*/
    Elite(1),
    /**英雄*/
    Hero(2),
    /**传奇*/
    Legend(3),
    /**boss*/
    Boss(4),
    /**助手*/
    Assistant(5),
    unk6(6),
    unk7(7);

    private byte id;

    ECharacterGradeType(final int id) {
        this.id = (byte) id;
    }

    public static ECharacterGradeType valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            throw new IllegalArgumentException("Invalid ECharacterGradeType id: " + reqType);
        }
        return values()[reqType];
    }

    public boolean isGreatherOrEqual(final ECharacterGradeType gradeType) {
        return this.ordinal() >= gradeType.ordinal();
    }
}
