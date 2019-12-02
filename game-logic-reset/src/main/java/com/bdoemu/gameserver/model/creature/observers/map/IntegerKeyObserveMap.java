package com.bdoemu.gameserver.model.creature.observers.map;

import com.bdoemu.gameserver.model.creature.observers.Observer;
import com.bdoemu.gameserver.model.creature.observers.enums.EObserveType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName IntegerKeyObserveMap
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 11:56
 * VERSION 1.0
 */

public class IntegerKeyObserveMap implements ObserveMap{

    private final ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Observer>> map;

    public IntegerKeyObserveMap() {
        this.map = new ConcurrentHashMap<>();
    }

    @Override
    public void put(final Observer observer) {
        final Integer key = (Integer) observer.getKey();
        synchronized (this.map) {
            this.map.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>()).add(observer);
        }
    }

    @Override
    public void remove(final Observer observer) {
        final Integer key = (Integer) observer.getKey();
        synchronized (this.map) {
            this.map.get(key).remove(observer);
            if (this.map.get(key).isEmpty()) {
                this.map.remove(key);
            }
        }
    }

    @Override
    public void notify(final EObserveType observeType, final Object... params) {
        final Integer key = (Integer) params[0];
        final ConcurrentLinkedQueue<Observer> observers = this.map.get(key);
        if (observers != null) {
            for (final Observer observer : observers) {
                observer.notify(observeType, params);
            }
        }
    }
}
