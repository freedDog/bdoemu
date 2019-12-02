package com.bdoemu.core.network;

import com.bdoemu.commons.network.Connection;
import com.bdoemu.commons.network.IClientFactory;
import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.login.LoginServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName LoginClientFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:12
 * VERSION 1.0
 */

@StartupComponent("Network")
public class LoginClientFactory implements IClientFactory<LoginClient> {
    private static final Logger log=LoggerFactory.getLogger(LoginClientFactory.class);
    private static final AtomicReference<LoginClientFactory> instance =new AtomicReference<>();

    @Override
    public LoginClient createClient(final Connection<LoginClient> connection) {
        return new LoginClient(connection);
    }

    public static LoginClientFactory getInstance() {
        Object o = instance.get();
        if (o == null) {
            synchronized (LoginClientFactory.instance) {
                o = instance.get();
                if (o == null) {
                    final LoginClientFactory loginClientFactory = new LoginClientFactory();
                    o = ((loginClientFactory == null) ? LoginClientFactory.instance : loginClientFactory);
                    instance.set(loginClientFactory);
                }
            }
        }
        return (LoginClientFactory)((o == LoginClientFactory.instance) ? null : o);
    }

}
