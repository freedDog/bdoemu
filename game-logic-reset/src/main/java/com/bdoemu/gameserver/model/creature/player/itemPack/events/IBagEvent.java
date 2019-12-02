package com.bdoemu.gameserver.model.creature.player.itemPack.events;

/**
 * @ClassName IBagEvent
 * @Description  背包事件
 * @Author JiangBangMing
 * @Date 2019/7/9 20:28
 * VERSION 1.0
 */

public interface IBagEvent {

    void onEvent();

    boolean canAct();

}
