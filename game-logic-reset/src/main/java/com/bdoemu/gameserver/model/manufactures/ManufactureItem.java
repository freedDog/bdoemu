package com.bdoemu.gameserver.model.manufactures;

/**
 * @ClassName ManufactureItem
 * @Description  制造道具
 * @Author JiangBangMing
 * @Date 2019/7/11 14:06
 * VERSION 1.0
 */

public class ManufactureItem {
    private int itemId;
    private long count;

    public ManufactureItem(final int itemId, final long count) {
        this.itemId = itemId;
        this.count = count;
    }

    public long getCount() {
        return this.count;
    }

    public int getItemId() {
        return this.itemId;
    }
}
