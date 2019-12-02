package com.bdoemu.gameserver.model.items;

import com.bdoemu.gameserver.model.items.templates.ItemSubGroupT;

/**
 * @ClassName ShopItem
 * @Description  商店道具
 * @Author JiangBangMing
 * @Date 2019/7/9 20:43
 * VERSION 1.0
 */

public class ShopItem {
    private ItemSubGroupT itemSubGroupT;
    private int type;
    private int chance;

    public ShopItem(final ItemSubGroupT itemSubGroupT, final int type, final int chance) {
        this.itemSubGroupT = itemSubGroupT;
        this.type = type;
        this.chance = chance;
    }

    public ItemSubGroupT getItemSubGroup() {
        return this.itemSubGroupT;
    }

    public int getType() {
        return this.type;
    }

    public int getChance() {
        return this.chance;
    }
}
