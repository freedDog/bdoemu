package com.bdoemu.gameserver.model.items.templates;

/**
 * @ClassName CashProductT
 * @Description 现金的产品
 * @Author JiangBangMing
 * @Date 2019/7/10 14:22
 * VERSION 1.0
 */

public class CashProductT {
    private final int itemId;
    private final long count;

    public CashProductT(final int itemId, final long count) {
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
