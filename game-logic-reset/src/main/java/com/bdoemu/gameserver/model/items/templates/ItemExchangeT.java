package com.bdoemu.gameserver.model.items.templates;

/**
 * @ClassName ItemExchangeT
 * @Description 物品交换模板
 * @Author JiangBangMing
 * @Date 2019/7/9 18:14
 * VERSION 1.0
 */

public class ItemExchangeT {

    private int itemId;
    private int enchantLevel;
    private int index;
    private long count;

    public ItemExchangeT(final int itemId, final int enchantLevel, final long count, final int index) {
        this.itemId = itemId;
        this.enchantLevel = enchantLevel;
        this.count = count;
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public long getCount() {
        return this.count;
    }

    public int getItemId() {
        return this.itemId;
    }

    public int getEnchantLevel() {
        return this.enchantLevel;
    }
}
