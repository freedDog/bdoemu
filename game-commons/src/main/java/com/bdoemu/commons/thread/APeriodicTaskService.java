package com.bdoemu.commons.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName APeriodicTaskService
 * @Description 周期性任务服务
 * @Author JiangBangMing
 * @Date 2019/6/24 17:02
 * VERSION 1.0
 */

public abstract class APeriodicTaskService implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(APeriodicTaskService.class);

    private final long period;

    private final TimeUnit timeUnit;
    private ScheduledFuture<?> task;

    protected APeriodicTaskService(long period, TimeUnit timeUnit, boolean withoutInitialDelay) {
        this.period = period;
        this.timeUnit = timeUnit;
        this.task = ThreadPool.getInstance().scheduleGeneralAtFixedRate(this, withoutInitialDelay ? 0L : period, period, timeUnit);
        log.info("{}: Initialized.", getClass().getSimpleName());
    }


    protected APeriodicTaskService(long period, TimeUnit timeUnit) {
        this(period, timeUnit, false);
    }

    @Override
    public abstract void run();

    public void cancel() {
        this.task.cancel(true);
    }
}
