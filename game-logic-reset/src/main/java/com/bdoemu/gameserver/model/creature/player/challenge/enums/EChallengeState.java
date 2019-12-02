package com.bdoemu.gameserver.model.creature.player.challenge.enums;

/**
 * @ClassName EChallengeState
 * @Description 挑战状态
 * @Author JiangBangMing
 * @Date 2019/7/10 12:23
 * VERSION 1.0
 */
public enum EChallengeState {
    None,
    Progress,
    Complete;

    public boolean isProgress() {
        return this == EChallengeState.Progress;
    }

    public boolean isComplete() {
        return this == EChallengeState.Complete;
    }
}
