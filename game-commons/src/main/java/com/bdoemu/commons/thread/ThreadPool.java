package com.bdoemu.commons.thread;

import com.bdoemu.core.configs.ThreadPoolConfig;
import com.bdoemu.core.startup.StartupComponent;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName ThreadPool
 * @Description 线程池
 * @Author JiangBangMing
 * @Date 2019/6/22 11:35
 * VERSION 1.0
 */

@StartupComponent("Threading")
public final class ThreadPool {
    private static final Logger log = LoggerFactory.getLogger(ThreadPool.class);
    private static final AtomicReference<Object> instance = new AtomicReference();

    public static ThreadPool getInstance() {
        Object value = instance.get();
        if (value == null) {
            synchronized (instance) {
                value = instance.get();
                if (value == null) {
                    ThreadPool actualValue = new ThreadPool();
                    value = (actualValue == null) ? instance : actualValue;
                    instance.set(value);
                }
            }
        }
        return (ThreadPool)((value == instance) ? null : value);
    }

    private ScheduledThreadPoolExecutor effectsScheduledThreadPool;

    private ScheduledThreadPoolExecutor generalScheduledThreadPool;
    private ScheduledThreadPoolExecutor aiScheduledThreadPool;
    private ScheduledThreadPoolExecutor networkScheduledThreadPool;
    private boolean _shutdown;

    protected ThreadPool() {
        this.effectsScheduledThreadPool = new ScheduledThreadPoolExecutor(ThreadPoolConfig.EFFECTS_CORE_POOL_SIZE * Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory("EffectsSTPool", 1));
        this.generalScheduledThreadPool = new ScheduledThreadPoolExecutor(ThreadPoolConfig.GENERAL_CORE_POOL_SIZE * Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory("GeneralSTPool", 5));
        this.aiScheduledThreadPool = new ScheduledThreadPoolExecutor(ThreadPoolConfig.AI_CORE_POOL_SIZE * Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory("AISTPool", 10));
        this.networkScheduledThreadPool = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory("NetworkSTPool", 10));
        scheduleGeneralAtFixedRate(new PurgeTask(), 5L, 5L, TimeUnit.MINUTES);
        log.info("ThreadPool initialized.");
    }

    public ScheduledThreadPoolExecutor getPacketExecutor() {
        return this.networkScheduledThreadPool;
    }

    public ScheduledFuture<?> scheduleEffect(Runnable task, long delay, TimeUnit unit) {
        try {
            return this.effectsScheduledThreadPool.schedule(new RunnableWrapper(task), delay, unit);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public ScheduledFuture<?> scheduleEffectAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        try {
            return this.effectsScheduledThreadPool.scheduleAtFixedRate(new RunnableWrapper(task), initialDelay, period, unit);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public ScheduledFuture<?> scheduleGeneral(Runnable task, long delay, TimeUnit unit) {
        try {
            return this.generalScheduledThreadPool.schedule(new RunnableWrapper(task), delay, unit);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public ScheduledFuture<?> scheduleGeneralAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        try {
            return this.generalScheduledThreadPool.scheduleAtFixedRate(new RunnableWrapper(task), initialDelay, period, unit);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public ScheduledFuture<?> scheduleAi(Runnable task, long delay, TimeUnit unit) {
        try {
            return this.aiScheduledThreadPool.schedule(new RunnableWrapper(task), delay, unit);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public ScheduledFuture<?> scheduleAiAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        try {
            return this.aiScheduledThreadPool.scheduleAtFixedRate(new RunnableWrapper(task), initialDelay, period, unit);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public void executeAi(Runnable task) {
        try {
            this.aiScheduledThreadPool.execute(new RunnableWrapper(task));
        } catch (RejectedExecutionException rejectedExecutionException) {}
    }

    public void executeGeneral(Runnable task) {
        try {
            this.generalScheduledThreadPool.execute(new RunnableWrapper(task));
        } catch (RejectedExecutionException rejectedExecutionException) {}
    }


    public void executeNetwork(Runnable task) {
        try {
            this.networkScheduledThreadPool.execute(new RunnableWrapper(task));
        } catch (RejectedExecutionException rejectedExecutionException) {}
    }

    public ScheduledFuture<?> scheduleNetwork(Runnable task, long delay, TimeUnit unit) {
        try {
            return this.networkScheduledThreadPool.schedule(new RunnableWrapper(task), delay, unit);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public String getStats() {
        StrBuilder builder = new StrBuilder();
        builder.appendln("STP:");
        builder.appendln(" + Effects:");
        builder.appendln(" |- ActiveThreads:   " + this.effectsScheduledThreadPool.getActiveCount());
        builder.appendln(" |- getCorePoolSize: " + this.effectsScheduledThreadPool.getCorePoolSize());
        builder.appendln(" |- PoolSize:        " + this.effectsScheduledThreadPool.getPoolSize());
        builder.appendln(" |- MaximumPoolSize: " + this.effectsScheduledThreadPool.getMaximumPoolSize());
        builder.appendln(" |- CompletedTasks:  " + this.effectsScheduledThreadPool.getCompletedTaskCount());
        builder.appendln(" |- ScheduledTasks:  " + this.effectsScheduledThreadPool.getQueue().size());
        builder.appendln(" | -------");
        builder.appendln(" + General:");
        builder.appendln(" |- ActiveThreads:   " + this.generalScheduledThreadPool.getActiveCount());
        builder.appendln(" |- getCorePoolSize: " + this.generalScheduledThreadPool.getCorePoolSize());
        builder.appendln(" |- PoolSize:        " + this.generalScheduledThreadPool.getPoolSize());
        builder.appendln(" |- MaximumPoolSize: " + this.generalScheduledThreadPool.getMaximumPoolSize());
        builder.appendln(" |- CompletedTasks:  " + this.generalScheduledThreadPool.getCompletedTaskCount());
        builder.appendln(" |- ScheduledTasks:  " + this.generalScheduledThreadPool.getQueue().size());
        builder.appendln(" | -------");
        builder.appendln(" + AI:");
        builder.appendln(" |- ActiveThreads:   " + this.aiScheduledThreadPool.getActiveCount());
        builder.appendln(" |- getCorePoolSize: " + this.aiScheduledThreadPool.getCorePoolSize());
        builder.appendln(" |- PoolSize:        " + this.aiScheduledThreadPool.getPoolSize());
        builder.appendln(" |- MaximumPoolSize: " + this.aiScheduledThreadPool.getMaximumPoolSize());
        builder.appendln(" |- CompletedTasks:  " + this.aiScheduledThreadPool.getCompletedTaskCount());
        builder.appendln(" |- ScheduledTasks:  " + this.aiScheduledThreadPool.getQueue().size());
        builder.appendln(" | -------");
        builder.appendln(" + Packet:");
        builder.appendln(" |- ActiveThreads:   " + this.networkScheduledThreadPool.getActiveCount());
        builder.appendln(" |- getCorePoolSize: " + this.networkScheduledThreadPool.getCorePoolSize());
        builder.appendln(" |- PoolSize:        " + this.networkScheduledThreadPool.getPoolSize());
        builder.appendln(" |- MaximumPoolSize: " + this.networkScheduledThreadPool.getMaximumPoolSize());
        builder.appendln(" |- CompletedTasks:  " + this.networkScheduledThreadPool.getCompletedTaskCount());
        builder.appendln(" |- ScheduledTasks:  " + this.networkScheduledThreadPool.getQueue().size());
        builder.appendln(" | -------");
        return builder.toString();
    }

    private static class PriorityThreadFactory implements ThreadFactory {
        /**优先级*/
        private final int priority;
        private final String name;
        private final AtomicInteger _threadNumber = new AtomicInteger(1);
        private final ThreadGroup group;

        PriorityThreadFactory(String name, int priority) {
            this.priority = priority;
            this.name = name;
            this.group = new ThreadGroup(this.name);
        }
        @Override
        public Thread newThread(Runnable runnable) {
            Thread t = new Thread(this.group, runnable, this.name + "-" + this._threadNumber.getAndIncrement());
            t.setPriority(this.priority);
            return t;
        }


        public ThreadGroup getGroup() { return this.group; }
    }


    public void shutdown() {
        this._shutdown = true;
        try {
            this.aiScheduledThreadPool.awaitTermination(1L, TimeUnit.SECONDS);
            this.effectsScheduledThreadPool.awaitTermination(1L, TimeUnit.SECONDS);
            this.generalScheduledThreadPool.awaitTermination(1L, TimeUnit.SECONDS);
            this.aiScheduledThreadPool.shutdown();
            this.effectsScheduledThreadPool.shutdown();
            this.generalScheduledThreadPool.shutdown();
            log.info("All ThreadPools are now stopped");
        } catch (InterruptedException e) {
            log.warn("There has been a problem shutting down the thread pool manager!", e);
        }
    }


    public boolean isShutdown() { return this._shutdown; }

    /**
     * 用于移除那些已被取消的任务
     */
    private class PurgeTask implements Runnable {
        private PurgeTask() {}

        @Override
        public void run() {
            ThreadPool.this.effectsScheduledThreadPool.purge();
            ThreadPool.this.generalScheduledThreadPool.purge();
            ThreadPool.this.aiScheduledThreadPool.purge();
        }
    }

    /**
     * 可运行包装器
     */
    private class RunnableWrapper implements Runnable {
        private final Runnable _r;

        private RunnableWrapper(Runnable r) {
            this._r = r;
        }

        @Override
        public final void run() {
            try {
                this._r.run();
            } catch (Exception e) {
                log.error("Error while running RunnableWrapper:", e);
                throw new RuntimeException(e);
            }
        }
    }
}
