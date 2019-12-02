package com.bdoemu.gameserver.model.misc.enums;

/**
 * @ClassName EServerShutdownType
 * @Description  服务器关闭类型
 * @Author JiangBangMing
 * @Date 2019/7/7 21:09
 * VERSION 1.0
 */
public enum EServerShutdownType {

    SIGTERM,
    /**关闭*/
    SHUTDOWN,
    /**重启*/
    RESTART,
    ABORT
}
