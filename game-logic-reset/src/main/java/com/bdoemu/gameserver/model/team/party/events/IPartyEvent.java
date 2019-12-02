package com.bdoemu.gameserver.model.team.party.events;

/**
 * @ClassName IPartyEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/9 16:12
 * VERSION 1.0
 */

public interface IPartyEvent {

    void onEvent();

    boolean canAct();
}
