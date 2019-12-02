package com.bdoemu.gameserver.model.trade.events;

import com.bdoemu.commons.model.enums.EStringTable;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.trade.Trade;

/**
 * @ClassName CancelItemExchangeEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 17:00
 * VERSION 1.0
 */

public class CancelItemExchangeEvent implements ITradeEvent{

    private Player player;
    private Trade trade;

    public CancelItemExchangeEvent(final Player player, final Trade trade) {
        this.player = player;
        this.trade = trade;
    }

    @Override
    public void onEvent() {
        final Player partner = this.trade.getPartner(this.player);
        partner.setTrade(null);
        this.player.setTrade(null);
//        partner.sendPacket(new SMCancelItemExchangeWithPlayer(EStringTable.eErrNoItemExchangePeerCancel));
    }

    @Override
    public boolean canAct() {
        return this.player.getTrade() == this.trade;
    }
}
