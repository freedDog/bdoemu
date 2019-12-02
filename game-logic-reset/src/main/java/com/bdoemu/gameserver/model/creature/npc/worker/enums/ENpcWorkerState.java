package com.bdoemu.gameserver.model.creature.npc.worker.enums;

/**
 * @ClassName ENpcWorkerState
 * @Description  NPC 工作者状态
 * @Author JiangBangMing
 * @Date 2019/7/10 10:25
 * VERSION 1.0
 */
public enum ENpcWorkerState {

    WorkSupervisor,
    WorkerMarket;

    public boolean isWorkSupervisor() {
        return this == ENpcWorkerState.WorkSupervisor;
    }

    public boolean isWorkerMarket() {
        return this == ENpcWorkerState.WorkerMarket;
    }

}
