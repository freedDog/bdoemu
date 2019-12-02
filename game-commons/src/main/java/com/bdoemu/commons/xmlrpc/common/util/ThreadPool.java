package com.bdoemu.commons.xmlrpc.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ThreadPool
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:16
 * VERSION 1.0
 */

public class ThreadPool {

    private final ThreadGroup threadGroup;
    private final int maxSize;
    private final List<Poolable> waitingThreads;
    private final List<Poolable> runningThreads;
    private final List<Task> waitingTasks;
    private int num;

    public ThreadPool(final int pMaxSize, final String pName) {
        this.waitingThreads = new ArrayList();
        this.runningThreads = new ArrayList();
        this.waitingTasks = new ArrayList();
        this.maxSize = pMaxSize;
        this.threadGroup = new ThreadGroup(pName);
    }

    private synchronized void remove(final Poolable pPoolable) {
        this.runningThreads.remove(pPoolable);
        this.waitingThreads.remove(pPoolable);
    }

    void repool(final Poolable pPoolable) {
        boolean discarding = false;
        Task task = null;
        Poolable poolable = null;
        synchronized (this) {
            if (this.runningThreads.remove(pPoolable)) {
                if (this.maxSize != 0 && this.runningThreads.size() + this.waitingThreads.size() >= this.maxSize) {
                    discarding = true;
                }
                else {
                    this.waitingThreads.add(pPoolable);
                    if (this.waitingTasks.size() > 0) {
                        task = this.waitingTasks.remove(this.waitingTasks.size() - 1);
                        poolable = this.getPoolable(task, false);
                    }
                }
            }
            else {
                discarding = true;
            }
            if (discarding) {
                this.remove(pPoolable);
            }
        }
        if (poolable != null) {
            poolable.start(task);
        }
        if (discarding) {
            pPoolable.shutdown();
        }
    }

    public boolean startTask(final Task pTask) {
        final Poolable poolable = this.getPoolable(pTask, false);
        if (poolable == null) {
            return false;
        }
        poolable.start(pTask);
        return true;
    }

    private synchronized Poolable getPoolable(final Task pTask, final boolean pQueue) {
        if (this.maxSize != 0 && this.runningThreads.size() >= this.maxSize) {
            if (pQueue) {
                this.waitingTasks.add(pTask);
            }
            return null;
        }
        Poolable poolable;
        if (this.waitingThreads.size() > 0) {
            poolable = this.waitingThreads.remove(this.waitingThreads.size() - 1);
        }
        else {
            poolable = new Poolable(this.threadGroup, this.num++);
        }
        this.runningThreads.add(poolable);
        return poolable;
    }

    @Deprecated
    public boolean addTask(final Task pTask) {
        final Poolable poolable = this.getPoolable(pTask, true);
        if (poolable != null) {
            poolable.start(pTask);
            return true;
        }
        return false;
    }

    public synchronized void shutdown() {
        while (!this.waitingThreads.isEmpty()) {
            final Poolable poolable = this.waitingThreads.remove(this.waitingThreads.size() - 1);
            poolable.shutdown();
        }
        while (!this.runningThreads.isEmpty()) {
            final Poolable poolable = this.runningThreads.remove(this.runningThreads.size() - 1);
            poolable.shutdown();
        }
    }

    public int getMaxThreads() {
        return this.maxSize;
    }

    public synchronized int getNumThreads() {
        return this.num;
    }

    private class Poolable
    {
        private volatile boolean shuttingDown;
        private Task task;
        private Thread thread;

        Poolable(final ThreadGroup pGroup, final int pNum) {
            (this.thread = new Thread(pGroup, pGroup.getName() + "-" + pNum) {
                @Override
                public void run() {
                    while (!Poolable.this.shuttingDown) {
                        final Task t = Poolable.this.getTask();
                        if (t == null) {
                            try {
                                synchronized (this) {
                                    if (Poolable.this.shuttingDown || Poolable.this.getTask() != null) {
                                        continue;
                                    }
                                    this.wait();
                                }
                            }
                            catch (InterruptedException ex) {}
                        }
                        else {
                            try {
                                t.run();
                                Poolable.this.resetTask();
                                ThreadPool.this.repool(Poolable.this);
                            }
                            catch (Throwable e) {
                                ThreadPool.this.remove(Poolable.this);
                                Poolable.this.shutdown();
                                Poolable.this.resetTask();
                            }
                        }
                    }
                }
            }).start();
        }

        synchronized void shutdown() {
            this.shuttingDown = true;
            final Task t = this.getTask();
            if (t != null && t instanceof InterruptableTask) {
                try {
                    ((InterruptableTask)t).shutdown();
                }
                catch (Throwable t2) {}
            }
            this.task = null;
            synchronized (this.thread) {
                this.thread.notify();
            }
        }

        private Task getTask() {
            return this.task;
        }

        private void resetTask() {
            this.task = null;
        }

        void start(final Task pTask) {
            this.task = pTask;
            synchronized (this.thread) {
                this.thread.notify();
            }
        }
    }

    public interface Task
    {
        void run() throws Throwable;
    }

    public interface InterruptableTask extends Task
    {
        void shutdown() throws Throwable;
    }
}
