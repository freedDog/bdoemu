package com.bdoemu.gameserver.model.items.enums;

/**
 * @ClassName EItemType
 * @Description  道具类型
 * @Author JiangBangMing
 * @Date 2019/7/9 17:19
 * VERSION 1.0
 */
public enum EItemType {
    Normal(0),
    Equip(1),
    Skill(2),
    Tent(3),
    Installation(4),
    Jewel(5),
    CannonBall(6),
    Mapae(7),
    Material(8),
    Interaction(9),
    ContentsEvent(10),
    ToVehicle(11);

    private byte id;

    EItemType(final int id) {
        this.id = (byte) id;
    }

    public static EItemType valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            return null;
        }
        return values()[reqType];
    }

    public boolean isEquip() {
        return this == EItemType.Equip;
    }

    public boolean isInstallation() {
        return this == EItemType.Installation;
    }

    public boolean isSkill() {
        return this == EItemType.Skill;
    }

    public boolean isToVehicle() {
        return this == EItemType.ToVehicle;
    }
}
