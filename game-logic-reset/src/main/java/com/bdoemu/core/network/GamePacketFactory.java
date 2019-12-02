// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network;

import com.bdoemu.commons.network.handler.XmlPacketHandlerFactory;
import com.bdoemu.core.startup.StartupComponent;

import java.util.concurrent.atomic.AtomicReference;

@StartupComponent("Network")
public class GamePacketFactory extends XmlPacketHandlerFactory<GameClient> {
    private static final AtomicReference<Object> instance;

    static {
        instance = new AtomicReference<>();
    }

    public static GamePacketFactory getInstance() {
        Object value = GamePacketFactory.instance.get();
        if (value == null) {
            synchronized (GamePacketFactory.instance) {
                value = GamePacketFactory.instance.get();
                if (value == null) {
                    final GamePacketFactory actualValue = new GamePacketFactory();
                    GamePacketFactory.instance.set(value);
                    value = ((actualValue == null) ? GamePacketFactory.instance : actualValue);
                }
            }
        }
        return (GamePacketFactory) ((value == GamePacketFactory.instance) ? null : value);
    }
}
