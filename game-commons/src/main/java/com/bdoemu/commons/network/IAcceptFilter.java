package com.bdoemu.commons.network;

import java.net.InetSocketAddress;

/**
 * @ClassName IAcceptFilter
 * @Description 接受滤波器
 * @Author JiangBangMing
 * @Date 2019/6/22 12:31
 * VERSION 1.0
 */

public interface IAcceptFilter {
    boolean isAllowedAddress(InetSocketAddress paramInetSocketAddress);
}
