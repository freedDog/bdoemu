package com.bdoemu.gameserver.model.items.enums;

/**
 * @ClassName EItemClassify
 * @Description 道具分类类型
 * @Author JiangBangMing
 * @Date 2019/7/9 17:17
 * VERSION 1.0
 */

public enum EItemClassify {
    Etc(0),
    MainWeapon(1),
    SubWeapon(2),
    Armor(3),
    Accessory(4),
    BlackStone(5),
    Jewel(6),
    Potion(7),
    Cook(8),
    PearlGoods(9),
    Housing(10),
    Vehicle(11),
    Mine(12),
    Wood(13),
    Seed(14),
    Leather(15),
    Fish(16),
    DyeAmpule(17);

    private byte id;

    private EItemClassify(final int id) {
        this.id = (byte) id;
    }

    public static EItemClassify valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            return null;
        }
        return values()[reqType];
    }

    public boolean isEtc() {
        return this == EItemClassify.Etc;
    }

    public boolean isArmor() {
        return this == EItemClassify.Armor;
    }

    public boolean isAccessory() {
        return this == EItemClassify.Accessory;
    }

    public boolean isWeapon() {
        return this == EItemClassify.MainWeapon || this == EItemClassify.SubWeapon;
    }

    public boolean isJewel() {
        return this == EItemClassify.Jewel;
    }

    public boolean isBlackStone() {
        return this == EItemClassify.BlackStone;
    }

    public boolean isVehicle() {
        return this == EItemClassify.Vehicle;
    }

    public boolean isDyeAmpule() {
        return this == EItemClassify.DyeAmpule;
    }
}
