package com.bdoemu.gameserver.model.creature.player.rewards.templates;

/**
 * @ClassName IntimacyRewardT
 * @Description 亲密度奖励模板
 * @Author JiangBangMing
 * @Date 2019/7/9 17:02
 * VERSION 1.0
 */

public class IntimacyRewardT {

    private int npcId;
    private int intimacy;

    public IntimacyRewardT(final String[] params) {
        this.npcId = Integer.parseInt(params[0]);
        this.intimacy = Integer.parseInt(params[1].trim());
    }

    public int getIntimacy() {
        return this.intimacy;
    }

    public int getNpcId() {
        return this.npcId;
    }
}
