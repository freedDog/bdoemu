package com.bdoemu.login.service;

import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.login.models.firewall.FirewallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName FirewallService
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:47
 * VERSION 1.0
 */

@StartupComponent("Network")
public class FirewallService {
    private static final Logger log= LoggerFactory.getLogger(FirewallService.class);
    private static FirewallFilter firewallFilter=new FirewallFilter();

    public static FirewallService getInstance() {
        return Holder.INSTANCE;
    }

    public static FirewallFilter getFirewallFilter() {
        return FirewallService.firewallFilter;
    }
    private static class Holder {
        static final FirewallService INSTANCE = new FirewallService();
    }

}
