package com.bdoemu.gameserver.model.world.region.enums;

/**
 * @ClassName ERegionType
 * @Description   地区类型
 * @Author JiangBangMing
 * @Date 2019/7/5 19:10
 * VERSION 1.0
 */
public enum ERegionType {
    /**小城镇*/
    MinorTown(0),
    /**主城镇*/
    MainTown(1),
    /**猎场*/
    Hunting(2),
    /**围城*/
    Siege(3),
    /**要塞*/
    Fortress(4),
    /**围攻城堡*/
    CastleInSiege(5),
    /**沙洲*/
    Arena(6);

    private byte id;

    ERegionType(final int id) {
        this.id = (byte) id;
    }

    public static ERegionType valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            return null;
        }
        return values()[reqType];
    }

    public boolean isMinorTown() {
        return this == ERegionType.MinorTown;
    }

    public boolean isMainTown() {
        return this == ERegionType.MainTown;
    }

    public boolean isArena() {
        return this == ERegionType.Arena;
    }
}
