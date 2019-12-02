package com.bdoemu.gameserver.model.team.guild.events;

/**
 * @ClassName IGuildEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 16:24
 * VERSION 1.0
 */

public interface IGuildEvent {

    void onEvent();

    boolean canAct();
}
