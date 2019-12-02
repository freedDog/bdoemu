package com.bdoemu.gameserver.model.creature.player.rewards.templates;

import com.bdoemu.gameserver.model.creature.player.lifeExperience.enums.ELifeExpType;

/**
 * @ClassName LifeExpRewardT
 * @Description  生活经验奖励
 * @Author JiangBangMing
 * @Date 2019/7/9 17:03
 * VERSION 1.0
 */

public class LifeExpRewardT {

    private ELifeExpType lifeType;
    private int lifeExp;

    public LifeExpRewardT(final String[] params) {
        this.lifeType = ELifeExpType.valueOf(Integer.parseInt(params[0]));
        this.lifeExp = Integer.parseInt(params[1]);
    }

    public ELifeExpType getLifeType() {
        return this.lifeType;
    }

    public int getLifeExp() {
        return this.lifeExp;
    }
}
