// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.quests.Quest;

public class SMCompleteQuest extends SendablePacket<GameClient> {
    private final Quest quest;

    public SMCompleteQuest(final Quest quest) {
        this.quest = quest;
    }

    protected void writeBody(final SendByteBuffer buffer) {
        buffer.writeH(this.quest.getQuestGroupId());
        buffer.writeH(this.quest.getQuestId());
    }
}
