package com.bdoemu.gameserver.model.creature;

import com.bdoemu.gameserver.model.creature.player.itemPack.AbstractAddItemPack;
import com.bdoemu.gameserver.model.creature.player.itemPack.EquipmentsBag;
import com.bdoemu.gameserver.model.creature.templates.CreatureTemplate;
import com.bdoemu.gameserver.model.creature.templates.SpawnPlacementT;

/**
 * @ClassName Playable
 * @Description 可以被玩家控制的对象基类
 * @Author JiangBangMing
 * @Date 2019/7/5 17:58
 * VERSION 1.0
 */

public abstract class Playable extends Creature{
    protected int equipSlotCacheCount;
    protected int basicCacheCount;

    public Playable(final int gameObjectId, final CreatureTemplate template, final SpawnPlacementT spawnPlacement) {
        super(gameObjectId, template, spawnPlacement);
        this.equipSlotCacheCount = 1;
        this.basicCacheCount = 1;
    }

    public abstract AbstractAddItemPack getInventory();

    public abstract EquipmentsBag getEquipments();

    public abstract void onLevelChange(boolean sendPacket);

    public void setLevel(final int level) {
        this.level = level;
    }

    public void recalculateBasicCacheCount() {
        ++this.basicCacheCount;
    }

    public int getBasicCacheCount() {
        return this.basicCacheCount;
    }

    public void recalculateEquipSlotCacheCount() {
        ++this.equipSlotCacheCount;
    }

    public int getEquipSlotCacheCount() {
        return (this.getEquipments() != null) ? ((this.getEquipments().getItemSize() == 0) ? 0 : this.equipSlotCacheCount) : 0;
    }
}
