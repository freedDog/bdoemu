// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.core.network.sendable.SMRideOnVehicle;
import com.bdoemu.core.network.sendable.SMSetGameTime;
import com.bdoemu.gameserver.model.creature.player.Player;

public class CMSetReadyToPlay extends ReceivablePacket<GameClient> {
    public CMSetReadyToPlay(final short opcode) {
        super(opcode);
    }

    protected void read() {
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            this.sendPacket((SendablePacket) new SMRideOnVehicle(player));
            this.sendPacket((SendablePacket) new SMSetGameTime());
            player.setReadyToPlay(true);
        }
    }
}
