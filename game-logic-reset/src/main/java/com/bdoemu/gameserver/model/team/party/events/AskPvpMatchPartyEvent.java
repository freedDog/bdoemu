package com.bdoemu.gameserver.model.team.party.events;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.team.party.IParty;
import com.bdoemu.gameserver.model.team.party.service.PartyService;

/**
 * @ClassName AskPvpMatchPartyEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 16:58
 * VERSION 1.0
 */

public class AskPvpMatchPartyEvent implements IPartyEvent{
    private Player player;
    private Player askPlayer;
    private IParty<Player> party;

    public AskPvpMatchPartyEvent(final Player player, final IParty<Player> party, final Player askPlayer) {
        this.player = player;
        this.askPlayer = askPlayer;
        this.party = party;
    }

    @Override
    public void onEvent() {
//        this.party.sendBroadcastPacket(new SMAskPvPBattle(this.askPlayer.getGameObjectId()));
    }

    @Override
    public boolean canAct() {
        return PartyService.getInstance().contains(this.party) && this.player.getParty() == this.party && !this.player.hasPvpMatch();
    }
}
