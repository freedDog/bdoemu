package com.bdoemu.commons.utils;

import com.bdoemu.core.startup.StartupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName DeadlockDetector
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 11:25
 * VERSION 1.0
 */

@StartupComponent("BeforeStart")
public class DeadlockDetector extends Thread{

    private static final Logger log;
    private static final AtomicReference<Object> instance;
    private static final int _sleepTime = 60000;
    private final ThreadMXBean tmx;

    private DeadlockDetector() {
        super("DeadlockDetector");
        this.tmx = ManagementFactory.getThreadMXBean();
        super.setDaemon(true);
        super.start();
    }

    @Override
    public final void run() {
        boolean deadlock = false;
        while (!deadlock) {
            try {
                final long[] ids = this.tmx.findDeadlockedThreads();
                if (ids != null) {
                    deadlock = true;
                    final ThreadInfo[] tis = this.tmx.getThreadInfo(ids, true, true);
                    final StringBuilder info = new StringBuilder();
                    info.append("DeadLock Found!\n");
                    for (final ThreadInfo ti : tis) {
                        info.append(ti.toString());
                    }
                    for (final ThreadInfo ti : tis) {
                        final LockInfo[] locks = ti.getLockedSynchronizers();
                        final MonitorInfo[] monitors = ti.getLockedMonitors();
                        if (locks.length != 0 || monitors.length != 0) {
                            ThreadInfo dl = ti;
                            info.append("Java-level deadlock:\n");
                            info.append('\t');
                            info.append(dl.getThreadName());
                            info.append(" is waiting to lock ");
                            info.append(dl.getLockInfo().toString());
                            info.append(" which is held by ");
                            info.append(dl.getLockOwnerName() + "\n");
                            while ((dl = this.tmx.getThreadInfo(new long[] { dl.getLockOwnerId() }, true, true)[0]).getThreadId() != ti.getThreadId()) {
                                info.append('\t');
                                info.append(dl.getThreadName());
                                info.append(" is waiting to lock ");
                                info.append(dl.getLockInfo().toString());
                                info.append(" which is held by ");
                                info.append(dl.getLockOwnerName() + "\n");
                            }
                        }
                    }
                    DeadlockDetector.log.warn(info.toString());
                }
                Thread.sleep(60000L);
            }
            catch (Exception e) {
                DeadlockDetector.log.error("DeadLockDetector: ", (Throwable)e);
            }
        }
    }

    public static DeadlockDetector getInstance() {
        Object value = DeadlockDetector.instance.get();
        if (value == null) {
            synchronized (DeadlockDetector.instance) {
                value = DeadlockDetector.instance.get();
                if (value == null) {
                    final DeadlockDetector actualValue = new DeadlockDetector();
                    value = ((actualValue == null) ? DeadlockDetector.instance : actualValue);
                    DeadlockDetector.instance.set(value);
                }
            }
        }
        return (DeadlockDetector)((value == DeadlockDetector.instance) ? null : value);
    }

    static {
        log = LoggerFactory.getLogger((Class)DeadlockDetector.class);
        instance = new AtomicReference<Object>();
    }
}
