package com.bdoemu.gameserver.model.creature.player.duel.enums;

/**
 * @ClassName EPvpMatchState
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 16:04
 * VERSION 1.0
 */
public enum EPvpMatchState {
    Duel(0),
    Unk1(1),
    Normal(2);

    private int id;

    EPvpMatchState(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
