// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.GameClient;

public class SMUnregisterPet extends SendablePacket<GameClient> {
    private long petObjectId;

    public SMUnregisterPet(final long petObjectId) {
        this.petObjectId = petObjectId;
    }

    protected void writeBody(final SendByteBuffer buffer) {
        buffer.writeQ(this.petObjectId);
    }
}
