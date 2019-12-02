package com.bdoemu.gameserver.model.creature.agrolist;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.team.party.IParty;
import com.bdoemu.gameserver.model.team.party.enums.EPartyLootType;

/**
 * @ClassName PartyAggroInfo
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 11:40
 * VERSION 1.0
 */

public class PartyAggroInfo extends AggroInfo{

    private final IParty<Player> party;
    private EPartyLootType lootType;

    public PartyAggroInfo(final IParty<Player> party) {
        this.party = party;
        this.lootType = party.getLootType();
    }

    public EPartyLootType getLootType() {
        return this.lootType;
    }

    public IParty<Player> getParty() {
        return this.party;
    }

    public void setPartyId(final long partyId) {
        this.partyId = partyId;
    }
}
