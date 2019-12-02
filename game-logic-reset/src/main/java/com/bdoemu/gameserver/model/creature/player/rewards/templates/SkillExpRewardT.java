package com.bdoemu.gameserver.model.creature.player.rewards.templates;

/**
 * @ClassName SkillExpRewardT
 * @Description  技能经验模板
 * @Author JiangBangMing
 * @Date 2019/7/9 17:01
 * VERSION 1.0
 */

public class SkillExpRewardT {

    private int exp;

    public SkillExpRewardT(final String param) {
        this.exp = Integer.parseInt(param);
    }

    public int getExp() {
        return this.exp;
    }
}
