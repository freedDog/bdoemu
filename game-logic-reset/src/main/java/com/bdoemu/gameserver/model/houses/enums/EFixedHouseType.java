package com.bdoemu.gameserver.model.houses.enums;

/**
 * @ClassName EFixedHouseType
 * @Description 固定的房子类型
 * @Author JiangBangMing
 * @Date 2019/7/10 15:35
 * VERSION 1.0
 */
public enum EFixedHouseType {
    Tent,
    House;

    public boolean isTent() {
        return this == EFixedHouseType.Tent;
    }
}
