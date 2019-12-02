package com.bdoemu.gameserver.model.creature.player.encyclopedia.enums;

/**
 * @ClassName EEncyclopediaType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 14:34
 * VERSION 1.0
 */
public enum EEncyclopediaType {

    Fishing;

    public static EEncyclopediaType valueOf(final int id) {
        if (id < 0 || id > values().length - 1) {
            throw new IllegalArgumentException("Invalid EEncyclopediaType  id: " + id);
        }
        return values()[id];
    }
}
