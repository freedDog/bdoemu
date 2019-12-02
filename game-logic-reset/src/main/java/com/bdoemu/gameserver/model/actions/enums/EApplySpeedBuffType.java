package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName EApplySpeedBuffType
 * @Description 应用速度调整器类型
 * @Author JiangBangMing
 * @Date 2019/7/10 15:31
 * VERSION 1.0
 */
public enum EApplySpeedBuffType {
    Move,
    Attack,
    Cast,
    None;

    public static EApplySpeedBuffType valueof(final int id) {
        if (id < 0 || id > values().length - 1) {
            throw new IllegalArgumentException("Found unknown EApplySpeedBuffType with id= " + id);
        }
        return values()[id];
    }

    public boolean isMove() {
        return this == EApplySpeedBuffType.Move;
    }
}
