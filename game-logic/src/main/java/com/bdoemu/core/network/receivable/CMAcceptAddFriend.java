// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.social.friends.events.AcceptAddFriendEvent;

public class CMAcceptAddFriend extends ReceivablePacket<GameClient> {
    private long accountId;

    public CMAcceptAddFriend(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.accountId = this.readQ();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            player.getFriendHandler().onEvent(new AcceptAddFriendEvent(player, this.accountId));
        }
    }
}
