package com.bdoemu.commons.rmi.model;

import com.bdoemu.commons.model.enums.EBanType;

import java.beans.ConstructorProperties;
import java.io.Serializable;

/**
 * @ClassName BanInfo
 * @Description 禁止信息
 * @Author JiangBangMing
 * @Date 2019/6/22 11:07
 * VERSION 1.0
 */

public class BanInfo implements Serializable {
    private EBanType banType;
    private long banEnd;

    public void setBanType(EBanType banType) {
        this.banType = banType;
    }
    public void setBanEnd(long banEnd) {
        this.banEnd = banEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BanInfo)){
            return false;
        }
        BanInfo other = (BanInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$banType = getBanType(), other$banType = other.getBanType();
        return ((this$banType == null) ? (other$banType != null) : !this$banType.equals(other$banType)) ? false : (!(getBanEnd() != other.getBanEnd()));
    }
    protected boolean canEqual(Object other) {
        return other instanceof BanInfo;
    }

    @Override
    public int hashCode() {
        int PRIME = 59,result = 1;
        Object $banType = getBanType();
        result = result * 59 + (($banType == null) ? 43 : $banType.hashCode()); long $banEnd = getBanEnd();
        return result * 59 + (int)($banEnd >>> 32 ^ $banEnd);
    }

    @Override
    public String toString() {
        return "BanInfo(banType=" + getBanType() + ", banEnd=" + getBanEnd() + ")";
    }
    @ConstructorProperties({"banType", "banEnd"})
    public BanInfo(EBanType banType, long banEnd) {
        this.banType = banType; this.banEnd = banEnd;
    }

    public EBanType getBanType() {
        return this.banType;
    }

    public long getBanEnd() {
        return this.banEnd;
    }
}
