package com.bdoemu.gameserver.model.creature.player.contribution.templates;

/**
 * @ClassName ContributionEXPT
 * @Description 贡献经验模板
 * @Author JiangBangMing
 * @Date 2019/7/10 12:14
 * VERSION 1.0
 */

public class ContributionEXPT {
    private int explorePoint;
    private int requireEXP;

    public ContributionEXPT(final int explorePoint, final int requireEXP) {
        this.explorePoint = explorePoint;
        this.requireEXP = requireEXP;
    }

    public int getExplorePoint() {
        return this.explorePoint;
    }

    public int getRequireEXP() {
        return this.requireEXP;
    }
}
