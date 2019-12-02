package com.bdoemu.gameserver.model.creature.servant.enums;

/**
 * @ClassName EServantSealType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 17:33
 * VERSION 1.0
 */
public enum EServantSealType {
    NORMAL,
    KILLED,
    LOGOUT,
    TAMING;

    public boolean isNormal() {
        return this == EServantSealType.NORMAL;
    }

    public boolean isLogout() {
        return this == EServantSealType.LOGOUT;
    }

    public boolean isKilled() {
        return this == EServantSealType.KILLED;
    }

    public boolean isTaming() {
        return this == EServantSealType.TAMING;
    }
}
