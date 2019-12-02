package com.bdoemu.gameserver.model.pvp;

/**
 * @ClassName ELocalWarStatusType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 16:01
 * VERSION 1.0
 */

public enum  ELocalWarStatusType {
    Waiting,
    Started,
    Ending;

    public boolean isStarted() {
        return this == ELocalWarStatusType.Started;
    }

    public boolean isEnding() {
        return this == ELocalWarStatusType.Ending;
    }

    public boolean isWaiting() {
        return this == ELocalWarStatusType.Waiting;
    }
}
