// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.Player;

public class CMGetChallengeReward extends ReceivablePacket<GameClient> {
    private int challengeId;
    private int selectIndex;

    public CMGetChallengeReward(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.challengeId = this.readH();
        this.selectIndex = this.readH();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            player.getChallengeHandler().reward(this.challengeId, this.selectIndex);
        }
    }
}
