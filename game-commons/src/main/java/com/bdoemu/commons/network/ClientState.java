package com.bdoemu.commons.network;

/**
 * @ClassName ClientState
 * @Description 客户端状态
 * @Author JiangBangMing
 * @Date 2019/6/22 15:31
 * VERSION 1.0
 */
public enum ClientState {
    /**无状态*/
    NULL,
    /**连接状态*/
    CONNECTED,
    AUTHED_GG,
    AUTHED,
    ENTERED,
    DISCONNECTED;
}
