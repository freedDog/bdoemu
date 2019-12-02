// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.Player;

public class CMGiveupQuest extends ReceivablePacket<GameClient> {
    private int groupId;
    private int questId;

    public CMGiveupQuest(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.groupId = this.readHD();
        this.questId = this.readHD();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            player.getPlayerQuestHandler().giveupQuest(this.groupId, this.questId);
        }
    }
}
