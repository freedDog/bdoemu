// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.service.GameTimeService;

public class SMUpdateWeatherManager extends SendablePacket<GameClient> {
    protected void writeBody(final SendByteBuffer buffer) {
        buffer.writeQ(GameTimeService.getServerTimeInMillis());
        buffer.writeQ(GameTimeService.getServerTimeInSecond());
    }
}
