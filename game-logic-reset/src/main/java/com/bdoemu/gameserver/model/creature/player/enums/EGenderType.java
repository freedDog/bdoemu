package com.bdoemu.gameserver.model.creature.player.enums;

/**
 * @ClassName EGenderType
 * @Description  性别类型
 * @Author JiangBangMing
 * @Date 2019/7/9 16:03
 * VERSION 1.0
 */
public enum EGenderType {

    Man,
    Woman;

    public boolean isWoman() {
        return this == EGenderType.Woman;
    }
}
