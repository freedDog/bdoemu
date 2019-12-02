package com.bdoemu.commons.collection;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName SetBlockingQueue
 * @Description Set阻塞队列
 * @Author JiangBangMing
 * @Date 2019/6/15 16:49
 * VERSION 1.0
 */

public class SetBlockingQueue<T> extends LinkedBlockingQueue<T> {
    private Set<T> set = Collections.newSetFromMap(new ConcurrentHashMap());

    @Override
    public boolean add(T t) {
        if (this.set.contains(t)) {
            return false;
        }
        this.set.add(t);
        return super.add(t);
    }

    @Override
    public T take() {
        T t = null;
        try {
            t = (T)super.take();
            this.set.remove(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return t;
    }
}
