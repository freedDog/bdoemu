package com.bdoemu.gameserver.model.trade.events;

/**
 * @ClassName ITradeEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 17:02
 * VERSION 1.0
 */

public interface ITradeEvent {

    void onEvent();

    boolean canAct();

}
