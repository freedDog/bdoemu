package com.bdoemu.gameserver.model.team.party.events;

import com.bdoemu.gameserver.model.items.enums.EItemGradeType;

/**
 * @ClassName EDistributionItemGrade
 * @Description 分配道具等级
 * @Author JiangBangMing
 * @Date 2019/7/9 16:15
 * VERSION 1.0
 */
public enum EDistributionItemGrade {

    green(1, EItemGradeType.green),
    blue(2, EItemGradeType.blue),
    yellow(3, EItemGradeType.yellow),
    orange(4, EItemGradeType.orange),
    white(5, EItemGradeType.white);

    private int id;
    private EItemGradeType itemGradeType;

    EDistributionItemGrade(final int id, final EItemGradeType itemGradeType) {
        this.id = id;
        this.itemGradeType = itemGradeType;
    }

    public static EDistributionItemGrade valueOf(final int reqType) {
        for (final EDistributionItemGrade distributionItemGrade : values()) {
            if (distributionItemGrade.getId() == reqType) {
                return distributionItemGrade;
            }
        }
        return null;
    }

    public EItemGradeType getItemGradeType() {
        return this.itemGradeType;
    }

    public int getId() {
        return this.id;
    }
}
