// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.itemPack.events.AlchemyUpgradeItemEvent;
import com.bdoemu.gameserver.model.items.enums.EItemStorageLocation;

public class CMAlchemyUpgrade extends ReceivablePacket<GameClient> {
    private EItemStorageLocation stoneStorageLocation;
    private EItemStorageLocation storageLocation;
    private int stoneSlotIndex;
    private int slotIndex;
    private long count;

    public CMAlchemyUpgrade(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.storageLocation = EItemStorageLocation.valueOf(this.readC());
        this.slotIndex = this.readCD();
        this.count = this.readQ();
        this.stoneStorageLocation = EItemStorageLocation.valueOf(this.readC());
        this.stoneSlotIndex = this.readCD();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            player.getPlayerBag().onEvent(new AlchemyUpgradeItemEvent(player, this.stoneStorageLocation, this.storageLocation, this.stoneSlotIndex, this.slotIndex, this.count));
        }
    }
}
