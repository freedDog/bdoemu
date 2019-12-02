package com.bdoemu.gameserver.model.creature.player.answer;

import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;

/**
 * @ClassName SkillAnswer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/9 19:53
 * VERSION 1.0
 */

public class SkillAnswer {

    private ActiveBuff activeBuff;

    public SkillAnswer(final ActiveBuff activeBuff) {
        this.activeBuff = activeBuff;
    }

    public ActiveBuff getActiveBuff() {
        return this.activeBuff;
    }
}
