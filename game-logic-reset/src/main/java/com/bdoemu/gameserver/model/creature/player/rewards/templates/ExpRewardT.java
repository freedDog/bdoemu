package com.bdoemu.gameserver.model.creature.player.rewards.templates;

/**
 * @ClassName ExpRewardT
 * @Description  经验奖励模板
 * @Author JiangBangMing
 * @Date 2019/7/9 17:01
 * VERSION 1.0
 */

public class ExpRewardT {

    private long exp;

    public ExpRewardT(final String param) {
        this.exp = Long.parseLong(param);
    }

    public long getExp() {
        return this.exp;
    }
}
