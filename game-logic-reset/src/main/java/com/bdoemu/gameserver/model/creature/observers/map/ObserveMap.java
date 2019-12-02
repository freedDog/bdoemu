package com.bdoemu.gameserver.model.creature.observers.map;

import com.bdoemu.gameserver.model.creature.observers.Observer;
import com.bdoemu.gameserver.model.creature.observers.enums.EObserveType;

/**
 * @ClassName ObserveMap
 * @Description 观察者集合
 * @Author JiangBangMing
 * @Date 2019/7/9 15:08
 * VERSION 1.0
 */

public interface ObserveMap {

    /**
     *添加
     * @param observer
     */
    void put(final Observer observer);

    /**
     * 移除
     * @param observer
     */
    void remove(final Observer observer);

    /**
     * 通知
     * @param observeType
     * @param p1
     */
    void notify(final EObserveType observeType, final Object... p1);
}
