package com.bdoemu.gameserver.model.creature.player.duel.enums;

/**
 * @ClassName EPvpMatchType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 15:27
 * VERSION 1.0
 */
public enum EPvpMatchType {
    Duel(1);

    private int id;

    EPvpMatchType(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
