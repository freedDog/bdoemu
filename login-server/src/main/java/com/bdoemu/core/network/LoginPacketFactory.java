package com.bdoemu.core.network;

import com.bdoemu.commons.network.handler.XmlPacketHandlerFactory;
import com.bdoemu.core.startup.StartupComponent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName LoginPacketFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:14
 * VERSION 1.0
 */

@StartupComponent("Network")
public class LoginPacketFactory extends XmlPacketHandlerFactory<LoginClient> {

    private static final AtomicReference<Object> instance;

    public static LoginPacketFactory getInstance() {
        Object o = LoginPacketFactory.instance.get();
        if (o == null) {
            synchronized (LoginPacketFactory.instance) {
                o = LoginPacketFactory.instance.get();
                if (o == null) {
                    final LoginPacketFactory loginPacketFactory = new LoginPacketFactory();
                    o = ((loginPacketFactory == null) ? LoginPacketFactory.instance : loginPacketFactory);
                    LoginPacketFactory.instance.set(o);
                }
            }
        }
        return (LoginPacketFactory)((o == LoginPacketFactory.instance) ? null : o);
    }

    static {
        instance = new AtomicReference<>();
    }
}
