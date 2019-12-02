package com.bdoemu.login.models.firewall;

import com.bdoemu.commons.network.IAcceptFilter;
import com.bdoemu.login.dataholders.xml.FirewallData;

import java.net.InetSocketAddress;

/**
 * @ClassName FirewallFilter
 * @Description 防火墙过滤
 * @Author JiangBangMing
 * @Date 2019/6/26 19:40
 * VERSION 1.0
 */

public class FirewallFilter implements IAcceptFilter {

    @Override
    public boolean isAllowedAddress(final InetSocketAddress inetSocketAddress) {
       return  !FirewallData.getInstance().getBlockedIps().contains(inetSocketAddress.getAddress().getHostAddress());
    }
}
