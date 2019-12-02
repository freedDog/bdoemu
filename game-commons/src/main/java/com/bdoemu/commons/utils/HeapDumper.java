package com.bdoemu.commons.utils;

import com.sun.management.HotSpotDiagnosticMXBean;

import javax.management.MBeanServer;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName HeapDumper
 * @Description 堆转储工具
 * @Author JiangBangMing
 * @Date 2019/6/24 18:06
 * VERSION 1.0
 */

public class HeapDumper {
    private static SimpleDateFormat formatter;
    private static HotSpotDiagnosticMXBean hotspotMBean;

    public static String dumpHeap(final String dumpsDir, final boolean live) {
        synchronized (HeapDumper.class) {
            if (HeapDumper.hotspotMBean == null) {
                HeapDumper.hotspotMBean = getHotspotMBean();
            }
        }
        try {
            String fullPath = dumpsDir + "/";
            new File(fullPath).mkdirs();
            fullPath += HeapDumper.formatter.format(new Date());
            if (live) {
                fullPath += ".live";
            }
            fullPath += ".hprof";
            HeapDumper.hotspotMBean.dumpHeap(fullPath, live);
            return fullPath;
        }
        catch (RuntimeException re) {
            throw re;
        }
        catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }

    private static HotSpotDiagnosticMXBean getHotspotMBean() {
        try {
            final MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            return ManagementFactory.newPlatformMXBeanProxy(server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
        }
        catch (RuntimeException re) {
            throw re;
        }
        catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }

    static {
        HeapDumper.formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
    }
}
