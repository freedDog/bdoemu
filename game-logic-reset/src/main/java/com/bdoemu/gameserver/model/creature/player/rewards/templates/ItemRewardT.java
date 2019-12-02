package com.bdoemu.gameserver.model.creature.player.rewards.templates;

/**
 * @ClassName ItemRewardT
 * @Description  道具奖励模板
 * @Author JiangBangMing
 * @Date 2019/7/9 17:06
 * VERSION 1.0
 */

public class ItemRewardT {

    private int itemId;
    private int count;
    private int enchantLevel;

    public ItemRewardT(final String[] params) {
        this.itemId = Integer.parseInt(params[0]);
        this.enchantLevel = Integer.parseInt(params[1]);
        this.count = Integer.parseInt(params[2]);
    }

    public int getCount() {
        return this.count;
    }

    public int getEnchantLevel() {
        return this.enchantLevel;
    }

    public int getItemId() {
        return this.itemId;
    }
}
