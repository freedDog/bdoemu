// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.servant.Servant;

public class SMChangeLookPet extends SendablePacket<GameClient> {
    private Servant pet;

    public SMChangeLookPet(final Servant pet) {
        this.pet = pet;
    }

    protected void writeBody(final SendByteBuffer buffer) {
        buffer.writeD(this.pet.getOwnerGameObjId());
        buffer.writeQ(this.pet.getObjectId());
        buffer.writeD(this.pet.getActionIndex());
    }
}
