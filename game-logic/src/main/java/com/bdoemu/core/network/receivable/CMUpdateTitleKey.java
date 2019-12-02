// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.Player;

public class CMUpdateTitleKey extends ReceivablePacket<GameClient> {
    private int titleId;
    private short category;

    public CMUpdateTitleKey(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.titleId = this.readD();
        this.category = this.readH();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            player.getTitleHandler().updateTitle(this.titleId);
        }
    }
}
