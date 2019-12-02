package com.bdoemu.gameserver.model.creature.player.challenge;

import com.bdoemu.gameserver.model.creature.player.challenge.enums.EChallengeState;
import com.bdoemu.gameserver.service.GameTimeService;

/**
 * @ClassName DailyChallenge
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 21:01
 * VERSION 1.0
 */

public class DailyChallenge extends AChallenge{

    public synchronized boolean check() {
        if (this.template.getProgressCondition() != null) {
            return false;
        }
        if (this.completeCount >= this.template.getConditionParam1()) {
            this.state = EChallengeState.Complete;
            return false;
        }
        if (this.state.isComplete()) {
            return false;
        }
        if (this.lastRewardedDate == 0L) {
            this.lastRewardedDate = GameTimeService.getServerTimeInMillis();
        } else if (GameTimeService.getServerTimeInMillis() - this.lastRewardedDate >= 86400000L) {
            ++this.completeCount;
            this.lastRewardedDate = GameTimeService.getServerTimeInMillis();
        }
        if (this.completeCount >= this.template.getConditionParam1()) {
            this.lastRewardedDate = 0L;
            this.state = EChallengeState.Complete;
            this.handler.setReward(this.template.getChallengeId());
            return true;
        }
        this.state = EChallengeState.Progress;
        return false;
    }

    @Override
    protected void init() {
        this.check();
        if (!this.state.isComplete()) {
//            this.player.sendPacket(new SMUpdateChallenge(this));
        }
    }
}
