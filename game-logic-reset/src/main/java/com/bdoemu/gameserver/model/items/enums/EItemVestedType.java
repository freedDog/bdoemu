package com.bdoemu.gameserver.model.items.enums;

/**
 * @ClassName EItemVestedType
 * @Description
 * @Author JiangBangMing
 * @Date 2019/7/9 17:26
 * VERSION 1.0
 */
public enum EItemVestedType {

    None(0),
    onRecive(1),
    onEquip(2);

    private byte type;

    private EItemVestedType(final int type) {
        this.type = (byte) type;
    }

    public static EItemVestedType valueOf(final int id) {
        for (final EItemVestedType type : values()) {
            if (type.getType() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid EItemVestedType id: " + id);
    }

    public byte getType() {
        return this.type;
    }

    public boolean isOnEquip() {
        return this == EItemVestedType.onEquip;
    }

    public boolean isOnRecive() {
        return this == EItemVestedType.onRecive;
    }
}
