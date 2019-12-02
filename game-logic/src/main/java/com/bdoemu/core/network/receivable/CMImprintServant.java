// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.enums.ECharKind;
import com.bdoemu.gameserver.model.creature.npc.Npc;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.itemPack.events.ImprintServantItemEvent;
import com.bdoemu.gameserver.model.creature.servant.Servant;
import com.bdoemu.gameserver.model.items.enums.EItemStorageLocation;
import com.bdoemu.gameserver.model.knowlist.KnowList;

public class CMImprintServant extends ReceivablePacket<GameClient> {
    private long servantObjectId;
    private int npcGameObjId;
    private int slotIndex;
    private boolean result;
    private EItemStorageLocation itemStorageLocation;

    public CMImprintServant(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.servantObjectId = this.readQ();
        this.npcGameObjId = this.readD();
        this.result = this.readCB();
        this.itemStorageLocation = EItemStorageLocation.valueOf(this.readC());
        this.slotIndex = this.readC();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            final Npc npc = KnowList.getObject(player, ECharKind.Npc, this.npcGameObjId);
            if (npc == null) {
                return;
            }
            final Servant servant = player.getServantController().getServant(this.servantObjectId);
            if (servant == null || servant.getRegionId() != npc.getRegionId()) {
                return;
            }
            if (servant.getServantState().isStable()) {
                player.getPlayerBag().onEvent(new ImprintServantItemEvent(player, servant, this.itemStorageLocation, this.slotIndex, this.result, npc.getRegionId()));
            }
        }
    }
}
