package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;
import com.bdoemu.commons.model.xmlrpc.XmlRpcMessageType;

/**
 * @ClassName XmlRpcPayment
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/27 21:33
 * VERSION 1.0
 */

public class XmlRpcPayment extends XmlRpcMessage {
    private Long paymentId;
    private String accountName;
    private double sum;
    private String paymentSystemName;
    private String paymentSystemTransactionId;
    private String comment;
    private XmlRpcPaymentStatus status;
    private Long createdDate;
    private Long paidDate;

    public XmlRpcPayment() {
    }

    public XmlRpcPayment(final XmlRpcMessageType type) {
        super(type);
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public double getSum() {
        return this.sum;
    }

    public String getPaymentSystemName() {
        return this.paymentSystemName;
    }

    public String getPaymentSystemTransactionId() {
        return this.paymentSystemTransactionId;
    }

    public String getComment() {
        return this.comment;
    }

    public XmlRpcPaymentStatus getStatus() {
        return this.status;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }

    public Long getPaidDate() {
        return this.paidDate;
    }

    public void setPaymentId(final Long paymentId) {
        this.paymentId = paymentId;
    }

    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    public void setSum(final double sum) {
        this.sum = sum;
    }

    public void setPaymentSystemName(final String paymentSystemName) {
        this.paymentSystemName = paymentSystemName;
    }

    public void setPaymentSystemTransactionId(final String paymentSystemTransactionId) {
        this.paymentSystemTransactionId = paymentSystemTransactionId;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setStatus(final XmlRpcPaymentStatus status) {
        this.status = status;
    }

    public void setCreatedDate(final Long createdDate) {
        this.createdDate = createdDate;
    }

    public void setPaidDate(final Long paidDate) {
        this.paidDate = paidDate;
    }

    @Override
    public String toString() {
        return "XmlRpcPayment(paymentId=" + this.getPaymentId() + ", accountName=" + this.getAccountName() + ", sum=" + this.getSum() + ", paymentSystemName=" + this.getPaymentSystemName() + ", paymentSystemTransactionId=" + this.getPaymentSystemTransactionId() + ", comment=" + this.getComment() + ", status=" + this.getStatus() + ", createdDate=" + this.getCreatedDate() + ", paidDate=" + this.getPaidDate() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcPayment)) {
            return false;
        }
        final XmlRpcPayment other = (XmlRpcPayment)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$paymentId = this.getPaymentId();
        final Object other$paymentId = other.getPaymentId();
        Label_0065: {
            if (this$paymentId == null) {
                if (other$paymentId == null) {
                    break Label_0065;
                }
            }
            else if (this$paymentId.equals(other$paymentId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$accountName = this.getAccountName();
        final Object other$accountName = other.getAccountName();
        Label_0102: {
            if (this$accountName == null) {
                if (other$accountName == null) {
                    break Label_0102;
                }
            }
            else if (this$accountName.equals(other$accountName)) {
                break Label_0102;
            }
            return false;
        }
        if (Double.compare(this.getSum(), other.getSum()) != 0) {
            return false;
        }
        final Object this$paymentSystemName = this.getPaymentSystemName();
        final Object other$paymentSystemName = other.getPaymentSystemName();
        Label_0155: {
            if (this$paymentSystemName == null) {
                if (other$paymentSystemName == null) {
                    break Label_0155;
                }
            }
            else if (this$paymentSystemName.equals(other$paymentSystemName)) {
                break Label_0155;
            }
            return false;
        }
        final Object this$paymentSystemTransactionId = this.getPaymentSystemTransactionId();
        final Object other$paymentSystemTransactionId = other.getPaymentSystemTransactionId();
        Label_0192: {
            if (this$paymentSystemTransactionId == null) {
                if (other$paymentSystemTransactionId == null) {
                    break Label_0192;
                }
            }
            else if (this$paymentSystemTransactionId.equals(other$paymentSystemTransactionId)) {
                break Label_0192;
            }
            return false;
        }
        final Object this$comment = this.getComment();
        final Object other$comment = other.getComment();
        Label_0229: {
            if (this$comment == null) {
                if (other$comment == null) {
                    break Label_0229;
                }
            }
            else if (this$comment.equals(other$comment)) {
                break Label_0229;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0266: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0266;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0266;
            }
            return false;
        }
        final Object this$createdDate = this.getCreatedDate();
        final Object other$createdDate = other.getCreatedDate();
        Label_0303: {
            if (this$createdDate == null) {
                if (other$createdDate == null) {
                    break Label_0303;
                }
            }
            else if (this$createdDate.equals(other$createdDate)) {
                break Label_0303;
            }
            return false;
        }
        final Object this$paidDate = this.getPaidDate();
        final Object other$paidDate = other.getPaidDate();
        if (this$paidDate == null) {
            if (other$paidDate == null) {
                return true;
            }
        }
        else if (this$paidDate.equals(other$paidDate)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcPayment;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $paymentId = this.getPaymentId();
        result = result * 59 + (($paymentId == null) ? 43 : $paymentId.hashCode());
        final Object $accountName = this.getAccountName();
        result = result * 59 + (($accountName == null) ? 43 : $accountName.hashCode());
        final long $sum = Double.doubleToLongBits(this.getSum());
        result = result * 59 + (int)($sum >>> 32 ^ $sum);
        final Object $paymentSystemName = this.getPaymentSystemName();
        result = result * 59 + (($paymentSystemName == null) ? 43 : $paymentSystemName.hashCode());
        final Object $paymentSystemTransactionId = this.getPaymentSystemTransactionId();
        result = result * 59 + (($paymentSystemTransactionId == null) ? 43 : $paymentSystemTransactionId.hashCode());
        final Object $comment = this.getComment();
        result = result * 59 + (($comment == null) ? 43 : $comment.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $createdDate = this.getCreatedDate();
        result = result * 59 + (($createdDate == null) ? 43 : $createdDate.hashCode());
        final Object $paidDate = this.getPaidDate();
        result = result * 59 + (($paidDate == null) ? 43 : $paidDate.hashCode());
        return result;
    }
}
