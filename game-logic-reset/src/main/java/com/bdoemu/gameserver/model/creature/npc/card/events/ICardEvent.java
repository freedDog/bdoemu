package com.bdoemu.gameserver.model.creature.npc.card.events;

/**
 * @ClassName ICardEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 10:47
 * VERSION 1.0
 */

public interface ICardEvent {

    void onEvent();

    boolean canAct();
}
