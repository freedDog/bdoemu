package com.bdoemu.gameserver.model.creature.npc.card.enums;

/**
 * @ClassName ECardGradeType
 * @Description   卡等级类型
 * @Author JiangBangMing
 * @Date 2019/7/10 10:39
 * VERSION 1.0
 */

public enum  ECardGradeType {

    C(1),
    B(2),
    A(3),
    A_PLUS(4),
    S(5);

    private int level;

    private ECardGradeType(final int level) {
        this.level = level;
    }

    public static ECardGradeType valueof(final int level) {
        for (final ECardGradeType type : values()) {
            if (type.getLevel() == level) {
                return type;
            }
        }
        return null;
    }

    public int getLevel() {
        return this.level;
    }
}
