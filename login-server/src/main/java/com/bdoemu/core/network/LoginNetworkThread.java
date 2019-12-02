package com.bdoemu.core.network;

import com.bdoemu.commons.network.IAcceptFilter;
import com.bdoemu.commons.network.IClientFactory;
import com.bdoemu.commons.network.NetworkThread;
import com.bdoemu.commons.network.handler.AbstractPacketHandlerFactory;
import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.login.models.firewall.FirewallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName LoginNetworkThread
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:13
 * VERSION 1.0
 */

@StartupComponent("Network")
public class LoginNetworkThread extends NetworkThread<LoginClient> {
    private static final Logger log;
    private static final AtomicReference<Object> instance;

    @Override
    public IClientFactory<LoginClient> getClientFactory() {
        return LoginClientFactory.getInstance();
    }

    @Override
    public AbstractPacketHandlerFactory<LoginClient> getPacketHandler() {
        return LoginPacketFactory.getInstance();
    }

    @Override
    public IAcceptFilter getAcceptFilter() {
        return super.getAcceptFilter();
    }

    public static LoginNetworkThread getInstance() {
        Object o =LoginNetworkThread.instance.get();
        if (o == null) {
            synchronized (LoginNetworkThread.instance) {
                o = LoginNetworkThread.instance.get();
                if (o == null) {
                    final LoginNetworkThread loginNetworkThread = new LoginNetworkThread();
                    o = ((loginNetworkThread == null) ? LoginNetworkThread.instance : loginNetworkThread);
                    LoginNetworkThread.instance.set(o);
                }
            }
        }
        return (LoginNetworkThread)((o == LoginNetworkThread.instance) ? null : o);
    }

    static {
        log = LoggerFactory.getLogger(LoginNetworkThread.class);
        instance = new AtomicReference<>();
    }

}
