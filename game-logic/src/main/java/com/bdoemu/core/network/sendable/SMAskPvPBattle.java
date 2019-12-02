// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.GameClient;

public class SMAskPvPBattle extends SendablePacket<GameClient> {
    private int gameObjectId;

    public SMAskPvPBattle(final int gameObjectId) {
        this.gameObjectId = gameObjectId;
    }

    protected void writeBody(final SendByteBuffer buffer) {
        buffer.writeD(this.gameObjectId);
        buffer.writeC(0);
    }
}
