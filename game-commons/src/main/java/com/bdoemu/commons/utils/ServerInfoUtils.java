package com.bdoemu.commons.utils;

import com.sun.management.HotSpotDiagnosticMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName ServerInfoUtils
 * @Description 服务信息帮助类
 * @Author JiangBangMing
 * @Date 2019/6/24 11:56
 * VERSION 1.0
 */

public class ServerInfoUtils {

    private static final Logger log = LoggerFactory.getLogger(ServerInfoUtils.class);

    public static void printSection(String sectionName) {
        StringBuilder sb = new StringBuilder(120);
        for (int i = 0; i < 117 - sectionName.length() - 2; i++) {
            sb.append('-');
        }
        sb.append("={ ").append(sectionName).append(" }");
        log.info(sb.toString());
    }

    public static String getDoneMessage(String s) {
        while (s.length() < 83) {
            s = s + " ";
        }
        return s + " ...done";
    }

    public static int getAvailableProcessors() {
        final Runtime   rt = Runtime.getRuntime();
        return rt.availableProcessors();
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }

    public static String getOSVersion() {
        return System.getProperty("os.version");
    }

    public static String getOSArch() {
        return System.getProperty("os.arch");
    }

    public static String[] getMemUsage() {
        return getMemoryUsageStatistics();
    }

    public static String formatNumber(long value) {
        return NumberFormat.getInstance(Locale.ENGLISH).format(value);
    }

    public static String[] getMemoryUsageStatistics() {
        double max = Runtime.getRuntime().maxMemory() / 1024.0D;
        double allocated = Runtime.getRuntime().totalMemory() / 1024.0D;
        double nonAllocated = max - allocated;
        double cached = Runtime.getRuntime().freeMemory() / 1024.0D;
        double used = allocated - cached;
        double useable = max - used;

        SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss");
        DecimalFormat df = new DecimalFormat(" (0.0000'%')");
        DecimalFormat df2 = new DecimalFormat(" # 'KB'");

        return new String[] { "+----", "| Global Memory Informations at " + sdf

                .format(new Date()) + ":", "|    |", "| Allowed Memory:" + df2

                .format(max), "|    |= Allocated Memory:" + df2
                .format(allocated) + df.format(allocated / max * 100.0D), "|    |= Non-Allocated Memory:" + df2
                .format(nonAllocated) + df.format(nonAllocated / max * 100.0D), "| Allocated Memory:" + df2
                .format(allocated), "|    |= Used Memory:" + df2
                .format(used) + df.format(used / max * 100.0D), "|    |= Unused (cached) Memory:" + df2
                .format(cached) + df.format(cached / max * 100.0D), "| Useable Memory:" + df2
                .format(useable) + df.format(useable / max * 100.0D), "+----" };
    }

    public static long usedMemory() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L;
    }

    public static void load(Class<?> clazz) {
        try {
            Class.forName(clazz.getName());
        }
        catch (ClassNotFoundException e) {

            throw new Error(e);
        }
    }


    public static String getUptime() {
        long uptimeInSec = (long)Math.ceil(ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0D);

        long s = uptimeInSec / 1L % 60L;
        long m = uptimeInSec / 60L % 60L;
        long h = uptimeInSec / 3600L % 24L;
        long d = uptimeInSec / 86400L;

        StringBuilder tb = new StringBuilder();

        if (d > 0L) {
            tb.append(d + " day(s), ");
        }

        if (h > 0L || tb.length() != 0) {
            tb.append(h + " hour(s), ");
        }

        if (m > 0L || tb.length() != 0) {
            tb.append(m + " minute(s), ");
        }

        if (s > 0L || tb.length() != 0) {
            tb.append(s + " second(s)");
        }

        return tb.toString();
    }

    public static String getShortUptime() {
        long uptimeInSec = (long)Math.ceil(ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0D);
        long s = uptimeInSec / 1L % 60L;
        long m = uptimeInSec / 60L % 60L;
        long h = uptimeInSec / 3600L % 24L;
        long d = uptimeInSec / 86400L;

        StringBuilder tb = new StringBuilder();

        if (d > 0L) {
            tb.append(d).append("d");
        }

        if (h > 0L || tb.length() != 0) {
            tb.append(h).append("h");
        }

        if (m > 0L || tb.length() != 0) {
            tb.append(m).append("m");
        }

        if (s > 0L || tb.length() != 0) {
            tb.append(s).append("s");
        }

        return tb.toString();
    }

    private static final class HotSpotDiagnosticMXBeanHolder {
        public static final HotSpotDiagnosticMXBean INSTANCE;

        static  {
            try {
                MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
                String mXBeanName = "com.sun.management:type=HotSpotDiagnostic";
                Class<HotSpotDiagnosticMXBean> mXBeanInterface = HotSpotDiagnosticMXBean.class;

                INSTANCE = (HotSpotDiagnosticMXBean)ManagementFactory.newPlatformMXBeanProxy(mBeanServer, "com.sun.management:type=HotSpotDiagnostic", mXBeanInterface);
            }
            catch (Exception e) {
                throw new Error(e);
            }
        } }
}
