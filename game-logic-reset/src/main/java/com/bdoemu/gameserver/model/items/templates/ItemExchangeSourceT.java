package com.bdoemu.gameserver.model.items.templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ItemExchangeSourceT
 * @Description  物品交换源模板
 * @Author JiangBangMing
 * @Date 2019/7/9 18:13
 * VERSION 1.0
 */

public class ItemExchangeSourceT {

    private int index;
    private int itemDropId;
    private int craftingTime;
    private List<ItemExchangeT> exchangeItems;

    public ItemExchangeSourceT(final ResultSet rs) throws SQLException {
        this.exchangeItems = new ArrayList<ItemExchangeT>();
        this.index = rs.getInt("Index");
        this.itemDropId = rs.getInt("ItemDropID");
        this.craftingTime = rs.getInt("CraftingTime");
        for (int index = 0; index < 6; ++index) {
            if (rs.getString("ItemIndex" + index) != null) {
                this.exchangeItems.add(new ItemExchangeT(rs.getInt("ItemIndex" + index), rs.getInt("ItemIndex" + index + "EnchantLevel"), rs.getLong("ItemCount" + index), index));
            }
        }
    }

    public List<ItemExchangeT> getExchangeItems() {
        return this.exchangeItems;
    }

    public ItemExchangeT getExchangeItem(final int itemId, final int enchantLevel) {
        for (final ItemExchangeT itemExchangeT : this.exchangeItems) {
            if (itemExchangeT.getItemId() == itemId && itemExchangeT.getEnchantLevel() == enchantLevel) {
                return itemExchangeT;
            }
        }
        return null;
    }

    public int getCraftingTime() {
        return this.craftingTime;
    }

    public int getIndex() {
        return this.index;
    }

    public int getItemDropId() {
        return this.itemDropId;
    }
}
