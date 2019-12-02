package com.bdoemu.gameserver.model.creature.servant.enums;

/**
 * @ClassName EServantType
 * @Description  仆从类型
 * @Author JiangBangMing
 * @Date 2019/7/9 0:24
 * VERSION 1.0
 */

public enum  EServantType {
    /**车辆*/
    Vehicle(0),
    /**船*/
    Ship(1),
    /**宠物*/
    Pet(2);

    private byte id;

    private EServantType(final int id) {
        this.id = (byte) id;
    }

    public static EServantType valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            return null;
        }
        return values()[reqType];
    }

    public boolean isShip() {
        return this == EServantType.Ship;
    }

    public boolean isVehicle() {
        return this == EServantType.Vehicle;
    }

    public boolean isPet() {
        return this == EServantType.Pet;
    }
}
