// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.GameClient;

public class SMCancelServantAuctionGoodsVer2 extends SendablePacket<GameClient> {
    private long objectId;

    public SMCancelServantAuctionGoodsVer2(final long objectId) {
        this.objectId = objectId;
    }

    protected void writeBody(final SendByteBuffer buffer) {
        buffer.writeQ(this.objectId);
    }
}
