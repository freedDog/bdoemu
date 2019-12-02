package com.bdoemu.gameserver.model.creature.player.cooltimes;

import com.bdoemu.gameserver.service.GameTimeService;

/**
 * @ClassName CoolTime
 * @Description  冷却时间
 * @Author JiangBangMing
 * @Date 2019/7/7 13:55
 * VERSION 1.0
 */

public class CoolTime {
    /**重用组*/
    private int reuseGroup;
    /**技能id*/
    private int skillId;
    /**类型*/
    private int type;
    /**结束时间*/
    private long endTime;

    public CoolTime(final int coolTime, final int reuseGroup, final int skillId, final int type) {
        this.reuseGroup = reuseGroup;
        this.skillId = skillId;
        this.type = type;
        this.endTime = GameTimeService.getServerTimeInMillis() + coolTime;
    }

    public int getReuseGroup() {
        return this.reuseGroup;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public int getSkillId() {
        return this.skillId;
    }

    public int getType() {
        return this.type;
    }

    public long getRemainigTime() {
        final long result = this.endTime - GameTimeService.getServerTimeInMillis();
        return (result < 0L) ? 0L : result;
    }
}
