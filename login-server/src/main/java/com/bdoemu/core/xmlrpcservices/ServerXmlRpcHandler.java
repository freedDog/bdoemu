package com.bdoemu.core.xmlrpcservices;

import com.bdoemu.commons.model.xmlrpc.BaseXmlRpcHandler;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcHardwareDiskInfo;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcHardwareInfo;
import com.bdoemu.commons.xmlrpc.XmlRpcHandler;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.lang3.StringUtils;
import sun.management.FileSystem;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.PlatformManagedObject;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ServerXmlRpcHandler
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:25
 * VERSION 1.0
 */

@XmlRpcHandler
public class ServerXmlRpcHandler extends BaseXmlRpcHandler {
    private static final File errorLog;

    public String isOnline() {
        return this.getMessageOk();
    }

    public String getHardwareInfo() {
        final XmlRpcHardwareInfo xmlRpcHardwareInfo = new XmlRpcHardwareInfo();
        xmlRpcHardwareInfo.setAllMemory(Runtime.getRuntime().totalMemory());
        xmlRpcHardwareInfo.setUsedMemory(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory());
        final OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        xmlRpcHardwareInfo.setCpuCount(operatingSystemMXBean.getAvailableProcessors());
        xmlRpcHardwareInfo.setCpuUsage(operatingSystemMXBean.getSystemLoadAverage());
        final ArrayList<XmlRpcHardwareDiskInfo> diskInfo = new ArrayList<>();
        for (final FileStore fileStore : FileSystems.getDefault().getFileStores()) {
            try {
                final XmlRpcHardwareDiskInfo xmlRpcHardwareDiskInfo = new XmlRpcHardwareDiskInfo();
                xmlRpcHardwareDiskInfo.setAllSpace(fileStore.getTotalSpace() / 1024L);
                xmlRpcHardwareDiskInfo.setUsedSpace((fileStore.getTotalSpace()-fileStore.getUnallocatedSpace()) / 1024L);
                xmlRpcHardwareDiskInfo.setDiskName(fileStore.name());
                diskInfo.add(xmlRpcHardwareDiskInfo);
            }
            catch (Exception ex) {}
        }
        xmlRpcHardwareInfo.setDiskInfo((List)diskInfo);
        return this.json(xmlRpcHardwareInfo);
    }

    public String getConsoleOutput() {
        try {
            final StringBuilder sb = new StringBuilder();
            int n;
            ReversedLinesFileReader reversedLinesFileReader;
            String line;
            for (n = 0, reversedLinesFileReader = new ReversedLinesFileReader(ServerXmlRpcHandler.errorLog); (line = reversedLinesFileReader.readLine()) != null && !StringUtils.isEmpty(line) && n < 3000; ++n) {
                sb.append(line);
            }
            reversedLinesFileReader.close();
            return this.json(sb.toString());
        }
        catch (IOException ex) {
            return null;
        }
    }

    static {
        errorLog = new File("");
    }
}
