package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName EStatType
 * @Description  状态类型
 * @Author JiangBangMing
 * @Date 2019/7/11 12:19
 * VERSION 1.0
 */
public enum EStatType {
    HP(0),
    MP(1),
    FP(2),
    SubResourcePoint(3);

    private int id;

    EStatType(final int id) {
        this.id = id;
    }

    public static EStatType valueof(final int id) {
        for (final EStatType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown EStatType:" + id);
    }

    public int getId() {
        return this.id;
    }
}
