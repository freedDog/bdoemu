package com.bdoemu.gameserver.model.creature.player.itemPack.events;

import com.bdoemu.commons.model.enums.EStringTable;
import com.bdoemu.core.configs.EtcOptionConfig;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.items.Item;
import com.bdoemu.gameserver.model.items.enums.EItemStorageLocation;

/**
 * @ClassName WorldChatWithItemEvent
 * @Description 世界聊天与道具事件
 * @Author JiangBangMing
 * @Date 2019/7/11 15:12
 * VERSION 1.0
 */

public class WorldChatWithItemEvent extends AItemEvent{

    private int slotIndex;

    public WorldChatWithItemEvent(final Player player, final int slotIndex) {
        super(player, player, player, EStringTable.NONE, EStringTable.NONE, 0);
        this.slotIndex = slotIndex;
    }

    @Override
    public void onEvent() {
        super.onEvent();
    }

    @Override
    public boolean canAct() {
        final Item item = this.cashInventory.getItem(this.slotIndex);
        this.decreaseItem(this.slotIndex, 1L, EItemStorageLocation.CashInventory);
        return super.canAct() && item != null && item.getItemId() == EtcOptionConfig.WORLD_CHAT_ITEM_KEY;
    }
}
