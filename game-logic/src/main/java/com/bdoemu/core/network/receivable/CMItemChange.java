// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.itemPack.events.ItemChangeEvent;
import com.bdoemu.gameserver.model.items.enums.EItemStorageLocation;

public class CMItemChange extends ReceivablePacket<GameClient> {
    private EItemStorageLocation ticketStorageType;
    private EItemStorageLocation itemStorageType;
    private int ticketSlotIndex;
    private int itemSlotIndex;

    public CMItemChange(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.ticketStorageType = EItemStorageLocation.valueOf(this.readC());
        this.ticketSlotIndex = this.readCD();
        this.itemStorageType = EItemStorageLocation.valueOf(this.readC());
        this.itemSlotIndex = this.readCD();
        this.readC();
        this.readC();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            player.getPlayerBag().onEvent(new ItemChangeEvent(player, this.ticketStorageType, this.ticketSlotIndex, this.itemStorageType, this.itemSlotIndex));
        }
    }
}
