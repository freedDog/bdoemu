package com.bdoemu.gameserver.model.team.party.service;

import com.bdoemu.commons.model.enums.EStringTable;
import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.services.InstanceSummonService;
import com.bdoemu.gameserver.model.team.party.IParty;
import com.bdoemu.gameserver.model.team.party.PlayerParty;
import com.bdoemu.gameserver.model.team.party.events.LoginPartyEvent;
import com.bdoemu.gameserver.model.team.party.events.NewPartyMemberEvent;
import com.bdoemu.gameserver.model.team.party.model.PartySearchEntry;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName PartyService
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 16:33
 * VERSION 1.0
 */

@StartupComponent("Service")
public class PartyService {
    private ConcurrentHashMap<Long, IParty<Player>> parties;
    private ConcurrentHashMap<Long, PartySearchEntry> partySearch;

    public PartyService() {
        this.parties = new ConcurrentHashMap<>();
        partySearch = new ConcurrentHashMap<>();
    }

    public static PartyService getInstance() {
        return Holder.INSTANCE;
    }

    public IParty<Player> getParty(final long partyId) {
        return this.parties.get(partyId);
    }

    public boolean contains(final IParty<Player> party) {
        return this.parties.values().contains(party);
    }

    public void onLogin(final Player player) {
        for (final IParty<Player> party : this.parties.values()) {
            if (party.getOfflineMembers().contains(player.getObjectId())) {
                party.onEvent(new LoginPartyEvent(party, player));
                break;
            }
        }
    }

    public void removeParty(final IParty<Player> party) {
        if (this.parties.remove(party.getPartyId()) != null) {
            party.cancelTask();
        }
    }

    public void createPartySearch() {

    }

    public synchronized void createParty(final Player partyLeader, final Player invitedMember) {
        if (InstanceSummonService.getInstance().contains(partyLeader.getAccountId()) || InstanceSummonService.getInstance().contains(invitedMember.getAccountId())) {
//            partyLeader.sendPacket(new SMNak(EStringTable.eErrNoDontInvitePartyBySummonMonter, CMAnswerInviteParty.class));
            return;
        }
        IParty<Player> party = partyLeader.getParty();
        if (party != null) {
            party.onEvent(new NewPartyMemberEvent(party, partyLeader, invitedMember, false));
        } else if (!partyLeader.hasParty() && !invitedMember.hasParty()) {
            party = new PlayerParty(partyLeader);
            this.parties.put(party.getPartyId(), party);
            invitedMember.setParty(party);
            party.onEvent(new NewPartyMemberEvent(party, partyLeader, partyLeader, true));
            party.onEvent(new NewPartyMemberEvent(party, partyLeader, invitedMember, true));
            party.startTask();
        }
    }

    public ConcurrentHashMap<Long, IParty<Player>> getParties() {
        return this.parties;
    }

    private static class Holder {
        static final PartyService INSTANCE = new PartyService();
    }

}
