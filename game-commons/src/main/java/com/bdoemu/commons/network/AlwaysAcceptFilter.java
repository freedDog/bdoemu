package com.bdoemu.commons.network;

import java.net.InetSocketAddress;

/**
 * @ClassName AlwaysAcceptFilter
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/22 15:47
 * VERSION 1.0
 */

public class AlwaysAcceptFilter  implements IAcceptFilter{

    @Override
    public boolean isAllowedAddress(InetSocketAddress address) {
        return true;
    }
}
