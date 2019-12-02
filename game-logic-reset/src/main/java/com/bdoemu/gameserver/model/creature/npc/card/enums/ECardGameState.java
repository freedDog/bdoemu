package com.bdoemu.gameserver.model.creature.npc.card.enums;

/**
 * @ClassName ECardGameState
 * @Description  卡游戏状态
 * @Author JiangBangMing
 * @Date 2019/7/10 10:44
 * VERSION 1.0
 */
public enum ECardGameState {
    SelectCards(1),
    TryCards(2),
    ReadyToReward(2),
    Rewarded(2);

    private byte id;

    private ECardGameState(final int id) {
        this.id = (byte) id;
    }

    public byte getId() {
        return this.id;
    }

    public boolean isSelectCards() {
        return this == ECardGameState.SelectCards;
    }

    public boolean isTryCards() {
        return this == ECardGameState.TryCards;
    }

    public boolean isReadyToReward() {
        return this == ECardGameState.ReadyToReward;
    }
}
