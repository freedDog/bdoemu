package com.bdoemu.gameserver.model.creature.npc.worker.events;

/**
 * @ClassName INpcWorkerEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 19:57
 * VERSION 1.0
 */

public interface INpcWorkerEvent {

    void onEvent();

    boolean canAct();
}
