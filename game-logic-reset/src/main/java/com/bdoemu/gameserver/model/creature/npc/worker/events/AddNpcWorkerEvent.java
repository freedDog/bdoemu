package com.bdoemu.gameserver.model.creature.npc.worker.events;

import com.bdoemu.gameserver.model.creature.npc.worker.NpcWorker;
import com.bdoemu.gameserver.model.creature.npc.worker.NpcWorkerController;
import com.bdoemu.gameserver.model.creature.player.Player;

import java.util.Collections;

/**
 * @ClassName AddNpcWorkerEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 10:08
 * VERSION 1.0
 */

public class AddNpcWorkerEvent implements INpcWorkerEvent{

    private Player player;
    private int characterKey;
    private int regionId;
    private NpcWorkerController npcWorkerController;
    private NpcWorker npcWorker;

    public AddNpcWorkerEvent(final Player player, final int characterKey, final int regionId) {
        this.player = player;
        this.characterKey = characterKey;
        this.regionId = regionId;
        this.npcWorkerController = player.getNpcWorkerController();
    }

    @Override
    public void onEvent() {
//        this.player.sendPacket(new SMAddMyNpcWorker(Collections.singletonList(this.npcWorker), EPacketTaskType.Update));
    }

    @Override
    public boolean canAct() {
        this.npcWorker = this.npcWorkerController.addNpcWorker(this.characterKey, this.regionId);
        return this.npcWorker != null;
    }
}
