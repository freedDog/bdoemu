package com.bdoemu.commons.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName CloseableReentrantLock
 * @Description 关闭可重入锁
 * @Author JiangBangMing
 * @Date 2019/6/15 16:51
 * VERSION 1.0
 */

public class CloseableReentrantLock extends ReentrantLock implements AutoCloseable {

    public CloseableReentrantLock open(){
        lock();
        return this;
    }
    @Override
    public void close() throws Exception {
        unlock();
    }
}
