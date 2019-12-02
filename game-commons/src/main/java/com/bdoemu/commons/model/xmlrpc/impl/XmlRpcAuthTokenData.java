package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;

/**
 * @ClassName XmlRpcAuthTokenData
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/27 12:27
 * VERSION 1.0
 */

public class XmlRpcAuthTokenData extends XmlRpcMessage {
    private String accountName;
    private String key;
    private long expireDate;

    public String getAccountName() {
        return this.accountName;
    }

    public String getKey() {
        return this.key;
    }

    public long getExpireDate() {
        return this.expireDate;
    }

    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public void setExpireDate(final long expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "XmlRpcAuthTokenData(accountName=" + this.getAccountName() + ", key=" + this.getKey() + ", expireDate=" + this.getExpireDate() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcAuthTokenData)) {
            return false;
        }
        final XmlRpcAuthTokenData other = (XmlRpcAuthTokenData)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$accountName = this.getAccountName();
        final Object other$accountName = other.getAccountName();
        Label_0065: {
            if (this$accountName == null) {
                if (other$accountName == null) {
                    break Label_0065;
                }
            }
            else if (this$accountName.equals(other$accountName)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null) {
            if (other$key == null) {
                return this.getExpireDate() == other.getExpireDate();
            }
        }
        else if (this$key.equals(other$key)) {
            return this.getExpireDate() == other.getExpireDate();
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcAuthTokenData;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accountName = this.getAccountName();
        result = result * 59 + (($accountName == null) ? 43 : $accountName.hashCode());
        final Object $key = this.getKey();
        result = result * 59 + (($key == null) ? 43 : $key.hashCode());
        final long $expireDate = this.getExpireDate();
        result = result * 59 + (int)($expireDate >>> 32 ^ $expireDate);
        return result;
    }
}
