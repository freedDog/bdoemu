package com.bdoemu.gameserver.model.houses.events;

/**
 * @ClassName IHouseEvent
 * @Description  房子时
 * @Author JiangBangMing
 * @Date 2019/7/9 18:16
 * VERSION 1.0
 */

public interface IHouseEvent {

    void onEvent();

    boolean canAct();
}
