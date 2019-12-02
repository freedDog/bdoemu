package com.bdoemu.gameserver.model.creature.player.itemPack.events;

import com.bdoemu.gameserver.model.items.enums.EItemStorageLocation;

/**
 * @ClassName DecreaseItemTask
 * @Description  减少道具任务
 * @Author JiangBangMing
 * @Date 2019/7/9 17:37
 * VERSION 1.0
 */

public class DecreaseItemTask {

    private final int slotIndex;
    private final long count;
    private long objectId;
    private EItemStorageLocation storageLocation;

    public DecreaseItemTask(final int slotIndex, final long count, final EItemStorageLocation storageLocation) {
        this.slotIndex = slotIndex;
        this.count = count;
        this.storageLocation = storageLocation;
    }

    public EItemStorageLocation getStorageLocation() {
        return this.storageLocation;
    }

    public int getSlotIndex() {
        return this.slotIndex;
    }

    public long getCount() {
        return this.count;
    }

    public long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(final long objectId) {
        this.objectId = objectId;
    }
}
