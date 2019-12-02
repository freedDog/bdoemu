package com.bdoemu.commons.model.xmlrpc.impl;

/**
 * @ClassName XmlRpcHardwareDiskInfo
 * @Description 硬件磁盘信息
 * @Author JiangBangMing
 * @Date 2019/6/28 11:17
 * VERSION 1.0
 */

public class XmlRpcHardwareDiskInfo {

    /**磁盘名*/
    private String diskName;
    /**所有空间*/
    private long allSpace;
    /**已用空间*/
    private long usedSpace;

    public String getDiskName() {
        return this.diskName;
    }

    public long getAllSpace() {
        return this.allSpace;
    }

    public long getUsedSpace() {
        return this.usedSpace;
    }

    public void setDiskName(final String diskName) {
        this.diskName = diskName;
    }

    public void setAllSpace(final long allSpace) {
        this.allSpace = allSpace;
    }

    public void setUsedSpace(final long usedSpace) {
        this.usedSpace = usedSpace;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcHardwareDiskInfo)) {
            return false;
        }
        final XmlRpcHardwareDiskInfo other = (XmlRpcHardwareDiskInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$diskName = this.getDiskName();
        final Object other$diskName = other.getDiskName();
        if (this$diskName == null) {
            if (other$diskName == null) {
                return this.getAllSpace() == other.getAllSpace() && this.getUsedSpace() == other.getUsedSpace();
            }
        }
        else if (this$diskName.equals(other$diskName)) {
            return this.getAllSpace() == other.getAllSpace() && this.getUsedSpace() == other.getUsedSpace();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcHardwareDiskInfo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $diskName = this.getDiskName();
        result = result * 59 + (($diskName == null) ? 43 : $diskName.hashCode());
        final long $allSpace = this.getAllSpace();
        result = result * 59 + (int)($allSpace >>> 32 ^ $allSpace);
        final long $usedSpace = this.getUsedSpace();
        result = result * 59 + (int)($usedSpace >>> 32 ^ $usedSpace);
        return result;
    }

    @Override
    public String toString() {
        return "XmlRpcHardwareDiskInfo(diskName=" + this.getDiskName() + ", allSpace=" + this.getAllSpace() + ", usedSpace=" + this.getUsedSpace() + ")";
    }
}
