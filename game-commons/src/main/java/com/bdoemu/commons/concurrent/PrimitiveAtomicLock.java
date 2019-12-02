package com.bdoemu.commons.concurrent;

import sun.misc.Contended;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName PrimitiveAtomicLock
 * @Description 原始原子锁
 * @Author JiangBangMing
 * @Date 2019/6/15 16:55
 * VERSION 1.0
 */

public class PrimitiveAtomicLock implements Lock {

    public static final int STATUS_LOCKED = 1;
    public static final int STATUS_UNLOCKED = 0;
    @Contended
    private final AtomicInteger status;

    public PrimitiveAtomicLock() {
        this.status = new AtomicInteger();
    }

    private AtomicInteger getStatus() {
        return this.status;
    }

    @Override
    public void lock() {
        AtomicInteger status = getStatus();
        while (!status.compareAndSet(0, 1)) {
            Thread.yield();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException{
        AtomicInteger status = getStatus();
        while (!status.compareAndSet(0, 1)) {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            Thread.yield();
        }
    }

    @Override
    public Condition newCondition() {
        throw new RuntimeException("Not supported.");
    }

    public boolean isLocked() {
        return (this.status.get() == 1);
    }

    @Override
    public boolean tryLock() {
        return this.status.compareAndSet(0, 1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        AtomicInteger status = getStatus();

        if (status.compareAndSet(0, 1)) {
            return true;
        }

        long resultTime = unit.toMillis(time);

        if (resultTime > 1L) {
            Thread.sleep(resultTime);
        }

        return status.compareAndSet(0, 1);
    }

    @Override
    public void unlock() {
        AtomicInteger status = getStatus();
        status.set(0);
    }
}
