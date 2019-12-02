package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;

import java.util.List;

/**
 * @ClassName XmlRpcHardwareInfo
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 11:16
 * VERSION 1.0
 */

public class XmlRpcHardwareInfo extends XmlRpcMessage {
    private long allMemory;
    private long usedMemory;
    private int cpuCount;
    private double cpuUsage;
    private List<XmlRpcHardwareDiskInfo> diskInfo;

    public long getAllMemory() {
        return this.allMemory;
    }

    public long getUsedMemory() {
        return this.usedMemory;
    }

    public int getCpuCount() {
        return this.cpuCount;
    }

    public double getCpuUsage() {
        return this.cpuUsage;
    }

    public List<XmlRpcHardwareDiskInfo> getDiskInfo() {
        return this.diskInfo;
    }

    public void setAllMemory(final long allMemory) {
        this.allMemory = allMemory;
    }

    public void setUsedMemory(final long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public void setCpuCount(final int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public void setCpuUsage(final double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public void setDiskInfo(final List<XmlRpcHardwareDiskInfo> diskInfo) {
        this.diskInfo = diskInfo;
    }

    @Override
    public String toString() {
        return "XmlRpcHardwareInfo(allMemory=" + this.getAllMemory() + ", usedMemory=" + this.getUsedMemory() + ", cpuCount=" + this.getCpuCount() + ", cpuUsage=" + this.getCpuUsage() + ", diskInfo=" + this.getDiskInfo() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcHardwareInfo)) {
            return false;
        }
        final XmlRpcHardwareInfo other = (XmlRpcHardwareInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getAllMemory() != other.getAllMemory()) {
            return false;
        }
        if (this.getUsedMemory() != other.getUsedMemory()) {
            return false;
        }
        if (this.getCpuCount() != other.getCpuCount()) {
            return false;
        }
        if (Double.compare(this.getCpuUsage(), other.getCpuUsage()) != 0) {
            return false;
        }
        final Object this$diskInfo = this.getDiskInfo();
        final Object other$diskInfo = other.getDiskInfo();
        if (this$diskInfo == null) {
            if (other$diskInfo == null) {
                return true;
            }
        }
        else if (this$diskInfo.equals(other$diskInfo)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcHardwareInfo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $allMemory = this.getAllMemory();
        result = result * 59 + (int)($allMemory >>> 32 ^ $allMemory);
        final long $usedMemory = this.getUsedMemory();
        result = result * 59 + (int)($usedMemory >>> 32 ^ $usedMemory);
        result = result * 59 + this.getCpuCount();
        final long $cpuUsage = Double.doubleToLongBits(this.getCpuUsage());
        result = result * 59 + (int)($cpuUsage >>> 32 ^ $cpuUsage);
        final Object $diskInfo = this.getDiskInfo();
        result = result * 59 + (($diskInfo == null) ? 43 : $diskInfo.hashCode());
        return result;
    }
}
