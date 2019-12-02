package com.bdoemu.gameserver.model.creature.player.itemPack.events;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.itemPack.PlayerEquipments;
import com.bdoemu.gameserver.model.items.Item;

/**
 * @ClassName VaryEquipItemEnduranceEvent
 * @Description 改变装备道具耐力
 * @Author JiangBangMing
 * @Date 2019/7/11 12:13
 * VERSION 1.0
 */

public class VaryEquipItemEnduranceEvent implements IBagEvent{
    private Player player;
    private int slotIndex;
    private int endurance;
    private PlayerEquipments equipments;
    private Item item;

    public VaryEquipItemEnduranceEvent(final Player player, final int slotIndex, final int endurance) {
        this.player = player;
        this.slotIndex = slotIndex;
        this.endurance = endurance;
        this.equipments = player.getPlayerBag().getEquipments();
    }

    @Override
    public void onEvent() {
        this.item.addEndurance(this.endurance);
//        this.player.sendBroadcastItSelfPacket(new SMVaryEquipItemEndurance(this.player.getGameObjectId(), this.slotIndex, this.endurance));
    }

    @Override
    public boolean canAct() {
        this.item = this.equipments.getItem(this.slotIndex);
        return this.item != null && this.item.getEndurance() + this.endurance >= 0;
    }
}
