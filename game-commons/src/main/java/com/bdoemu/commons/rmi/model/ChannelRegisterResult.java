package com.bdoemu.commons.rmi.model;

import java.io.Serializable;

/**
 * @ClassName ChannelRegisterResult
 * @Description 通道注册结果
 * @Author JiangBangMing
 * @Date 2019/6/21 20:46
 * VERSION 1.0
 */

public  enum ChannelRegisterResult implements Serializable {
    SUCCESS,
    DISABLED_SERVER,
    UNKNOWN_SERVER,
    ALREADY_REGISTERED;
}
