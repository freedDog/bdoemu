package com.bdoemu.gameserver.model.creature.player.social.friends.events;

/**
 * @ClassName IFriendEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 14:12
 * VERSION 1.0
 */

public interface IFriendEvent {

    void onEvent();

    boolean canAct();
}
