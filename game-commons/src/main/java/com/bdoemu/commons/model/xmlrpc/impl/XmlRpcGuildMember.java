package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;

/**
 * @ClassName XmlRpcGuildMember
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 20:03
 * VERSION 1.0
 */

public class XmlRpcGuildMember extends XmlRpcMessage {
    private long accountId;
    private String familyName;
    private long joinedDate;
    private long lastLoginDate;
    private long lastLogoutDate;
    private int rank;

    public long getAccountId() {
        return this.accountId;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public long getJoinedDate() {
        return this.joinedDate;
    }

    public long getLastLoginDate() {
        return this.lastLoginDate;
    }

    public long getLastLogoutDate() {
        return this.lastLogoutDate;
    }

    public int getRank() {
        return this.rank;
    }

    public void setAccountId(final long accountId) {
        this.accountId = accountId;
    }

    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    public void setJoinedDate(final long joinedDate) {
        this.joinedDate = joinedDate;
    }

    public void setLastLoginDate(final long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setLastLogoutDate(final long lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
    }

    public void setRank(final int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "XmlRpcGuildMember(accountId=" + this.getAccountId() + ", familyName=" + this.getFamilyName() + ", joinedDate=" + this.getJoinedDate() + ", lastLoginDate=" + this.getLastLoginDate() + ", lastLogoutDate=" + this.getLastLogoutDate() + ", rank=" + this.getRank() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcGuildMember)) {
            return false;
        }
        final XmlRpcGuildMember other = (XmlRpcGuildMember)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getAccountId() != other.getAccountId()) {
            return false;
        }
        final Object this$familyName = this.getFamilyName();
        final Object other$familyName = other.getFamilyName();
        if (this$familyName == null) {
            if (other$familyName == null) {
                return this.getJoinedDate() == other.getJoinedDate() && this.getLastLoginDate() == other.getLastLoginDate() && this.getLastLogoutDate() == other.getLastLogoutDate() && this.getRank() == other.getRank();
            }
        }
        else if (this$familyName.equals(other$familyName)) {
            return this.getJoinedDate() == other.getJoinedDate() && this.getLastLoginDate() == other.getLastLoginDate() && this.getLastLogoutDate() == other.getLastLogoutDate() && this.getRank() == other.getRank();
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcGuildMember;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $accountId = this.getAccountId();
        result = result * 59 + (int)($accountId >>> 32 ^ $accountId);
        final Object $familyName = this.getFamilyName();
        result = result * 59 + (($familyName == null) ? 43 : $familyName.hashCode());
        final long $joinedDate = this.getJoinedDate();
        result = result * 59 + (int)($joinedDate >>> 32 ^ $joinedDate);
        final long $lastLoginDate = this.getLastLoginDate();
        result = result * 59 + (int)($lastLoginDate >>> 32 ^ $lastLoginDate);
        final long $lastLogoutDate = this.getLastLogoutDate();
        result = result * 59 + (int)($lastLogoutDate >>> 32 ^ $lastLogoutDate);
        result = result * 59 + this.getRank();
        return result;
    }
}
