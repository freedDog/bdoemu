package com.bdoemu.gameserver.model.creature.observers;

import com.bdoemu.gameserver.model.creature.observers.enums.EObserveType;

/**
 * @ClassName Observer
 * @Description  观察者
 * @Author JiangBangMing
 * @Date 2019/7/9 15:09
 * VERSION 1.0
 */

public class Observer {

    private final EObserveType observeType;
    private Object key;

    public Observer(final EObserveType observeType) {
        this.observeType = observeType;
    }

    public Observer(final EObserveType observeType, final Object key) {
        this.key = key;
        this.observeType = observeType;
    }

    public void notify(final EObserveType type, final Object... params) {
    }

    public Object getKey() {
        return this.key;
    }

    public EObserveType getObserveType() {
        return this.observeType;
    }
}
