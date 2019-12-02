package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName ERotateType
 * @Description  旋转类型
 * @Author JiangBangMing
 * @Date 2019/7/11 11:39
 * VERSION 1.0
 */
public enum ERotateType {

    SelfDir,
    ToTarget,
    ToSelfPlayer,
    Original,
    Unk;

    public static ERotateType valueof(final int id) {
        if (id < 0 || id > values().length - 1) {
            throw new IllegalArgumentException("Unknow ERotateType:" + id);
        }
        return values()[id];
    }
}
