package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.enums.ELogEntryType;
import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;
import com.bdoemu.commons.model.xmlrpc.XmlRpcMessageType;

/**
 * @ClassName XmlRpcLog
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/27 21:27
 * VERSION 1.0
 */

public class XmlRpcLog extends XmlRpcMessage {

    private long id;
    private long date;
    private ELogEntryType logType;
    private long accountId;
    private String ip;
    private String comment;

    public XmlRpcLog() {
    }

    public XmlRpcLog(final XmlRpcMessageType type) {
        super(type);
    }

    public long getId() {
        return this.id;
    }

    public long getDate() {
        return this.date;
    }

    public ELogEntryType getLogType() {
        return this.logType;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public String getIp() {
        return this.ip;
    }

    public String getComment() {
        return this.comment;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setDate(final long date) {
        this.date = date;
    }

    public void setLogType(final ELogEntryType logType) {
        this.logType = logType;
    }

    public void setAccountId(final long accountId) {
        this.accountId = accountId;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "XmlRpcLog(id=" + this.getId() + ", date=" + this.getDate() + ", logType=" + this.getLogType() + ", accountId=" + this.getAccountId() + ", ip=" + this.getIp() + ", comment=" + this.getComment() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcLog)) {
            return false;
        }
        final XmlRpcLog other = (XmlRpcLog)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        if (this.getDate() != other.getDate()) {
            return false;
        }
        final Object this$logType = this.getLogType();
        final Object other$logType = other.getLogType();
        Label_0093: {
            if (this$logType == null) {
                if (other$logType == null) {
                    break Label_0093;
                }
            }
            else if (this$logType.equals(other$logType)) {
                break Label_0093;
            }
            return false;
        }
        if (this.getAccountId() != other.getAccountId()) {
            return false;
        }
        final Object this$ip = this.getIp();
        final Object other$ip = other.getIp();
        Label_0144: {
            if (this$ip == null) {
                if (other$ip == null) {
                    break Label_0144;
                }
            }
            else if (this$ip.equals(other$ip)) {
                break Label_0144;
            }
            return false;
        }
        final Object this$comment = this.getComment();
        final Object other$comment = other.getComment();
        if (this$comment == null) {
            if (other$comment == null) {
                return true;
            }
        }
        else if (this$comment.equals(other$comment)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcLog;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        final long $date = this.getDate();
        result = result * 59 + (int)($date >>> 32 ^ $date);
        final Object $logType = this.getLogType();
        result = result * 59 + (($logType == null) ? 43 : $logType.hashCode());
        final long $accountId = this.getAccountId();
        result = result * 59 + (int)($accountId >>> 32 ^ $accountId);
        final Object $ip = this.getIp();
        result = result * 59 + (($ip == null) ? 43 : $ip.hashCode());
        final Object $comment = this.getComment();
        result = result * 59 + (($comment == null) ? 43 : $comment.hashCode());
        return result;
    }
}
