package com.bdoemu.gameserver.model.team;

import com.bdoemu.gameserver.model.creature.services.FamilyService;

/**
 * @ClassName PartyItemWinner
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 11:23
 * VERSION 1.0
 */

public class PartyItemWinner {
    private final long accountId;
    private int dice;

    public PartyItemWinner(final long accountId) {
        this.accountId = accountId;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public String getFamily() {
        return FamilyService.getInstance().getFamily(this.accountId);
    }

    public int getDice() {
        return this.dice;
    }

    public void setDice(final int dice) {
        this.dice = dice;
    }
}
