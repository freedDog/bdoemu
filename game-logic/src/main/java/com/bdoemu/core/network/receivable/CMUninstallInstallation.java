// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.itemPack.events.UninstallInstallationItemEvent;

public class CMUninstallInstallation extends ReceivablePacket<GameClient> {
    private long houseObjectId;
    private long installationObjId;

    public CMUninstallInstallation(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.houseObjectId = this.readQ();
        this.installationObjId = this.readQ();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            player.getPlayerBag().onEvent(new UninstallInstallationItemEvent(player, this.houseObjectId, this.installationObjId));
        }
    }
}
