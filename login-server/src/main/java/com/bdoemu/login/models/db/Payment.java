package com.bdoemu.login.models.db;

import com.bdoemu.commons.database.mongo.JSONable;
import com.bdoemu.commons.model.xmlrpc.XMLRPCable;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcPayment;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcPaymentStatus;
import com.bdoemu.login.idfactory.LSIDStorageType;
import com.bdoemu.login.idfactory.LoginServerIDFactory;
import com.bdoemu.login.models.PaymentStatus;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName Payment
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:39
 * VERSION 1.0
 */

public class Payment extends JSONable implements XMLRPCable {
    private long objectId;
    private String accountName;
    private double sum;
    private String paymentSystemName;
    private String paymentSystemTransactionId;
    private String comment;
    private PaymentStatus status;
    private long createdDate;
    private long paidDate;

    public Payment() {
        this.objectId = LoginServerIDFactory.getInstance().nextId(LSIDStorageType.PAYMENT);
        this.createdDate = System.currentTimeMillis();
    }

    public Payment(final BasicDBObject basicDBObject) {
        this.objectId = basicDBObject.getLong("");
        this.accountName = basicDBObject.getString("");
        this.sum = basicDBObject.getDouble("");
        this.paymentSystemName = basicDBObject.getString("");
        this.paymentSystemTransactionId = basicDBObject.getString("");
        this.comment = basicDBObject.getString("");
        this.status = PaymentStatus.values()[basicDBObject.getInt("")];
        this.createdDate = basicDBObject.getLong("");
        this.paidDate = basicDBObject.getLong("");
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder basicDBObjectBuilder = new BasicDBObjectBuilder();
        basicDBObjectBuilder.add("", this.objectId);
        basicDBObjectBuilder.add("", (Object)this.accountName);
        basicDBObjectBuilder.add("", this.sum);
        basicDBObjectBuilder.add("",this.paymentSystemName);
        basicDBObjectBuilder.add("", (Object)this.paymentSystemTransactionId);
        basicDBObjectBuilder.add("", this.comment);
        basicDBObjectBuilder.add("", this.status);
        basicDBObjectBuilder.add("", this.createdDate);
        basicDBObjectBuilder.add("", this.paidDate);
        return basicDBObjectBuilder.get();
    }

    @Override
    public XmlRpcPayment toXMLRpcObject(final String message) {
        final XmlRpcPayment xmlRpcPayment = new XmlRpcPayment();
        xmlRpcPayment.setPaymentId( this.objectId);
        xmlRpcPayment.setAccountName(this.accountName);
        xmlRpcPayment.setSum(this.sum);
        xmlRpcPayment.setPaymentSystemName(this.paymentSystemName);
        xmlRpcPayment.setPaymentSystemTransactionId(this.paymentSystemTransactionId);
        xmlRpcPayment.setComment(this.comment);
        xmlRpcPayment.setStatus(XmlRpcPaymentStatus.values()[this.status.ordinal()]);
        xmlRpcPayment.setCreatedDate( this.createdDate);
        xmlRpcPayment.setPaidDate(this.paidDate);
        if (message != null) {
            xmlRpcPayment.setMessage(message);
        }
        return xmlRpcPayment;
    }
    @Override
    public long getObjectId() {
        return this.objectId;
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

    public PaymentStatus getStatus() {
        return this.status;
    }

    public long getCreatedDate() {
        return this.createdDate;
    }

    public long getPaidDate() {
        return this.paidDate;
    }

    public void setObjectId(final long objectId) {
        this.objectId = objectId;
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

    public void setStatus(final PaymentStatus status) {
        this.status = status;
    }

    public void setCreatedDate(final long createdDate) {
        this.createdDate = createdDate;
    }

    public void setPaidDate(final long paidDate) {
        this.paidDate = paidDate;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        final Payment payment = (Payment)o;
        if (!payment.canEqual(this)) {
            return false;
        }
        if (this.getObjectId() != payment.getObjectId()) {
            return false;
        }
        final String accountName = this.getAccountName();
        final String accountName2 = payment.getAccountName();
        Label_0079: {
            if (accountName == null) {
                if (accountName2 == null) {
                    break Label_0079;
                }
            }
            else if (accountName.equals(accountName2)) {
                break Label_0079;
            }
            return false;
        }
        final String paymentSystemName = this.getPaymentSystemName();
        final String paymentSystemName2 = payment.getPaymentSystemName();
        Label_0134: {
            if (paymentSystemName == null) {
                if (paymentSystemName2 == null) {
                    break Label_0134;
                }
            }
            else if (paymentSystemName.equals(paymentSystemName2)) {
                break Label_0134;
            }
            return false;
        }
        final String paymentSystemTransactionId = this.getPaymentSystemTransactionId();
        final String paymentSystemTransactionId2 = payment.getPaymentSystemTransactionId();
        Label_0171: {
            if (paymentSystemTransactionId == null) {
                if (paymentSystemTransactionId2 == null) {
                    break Label_0171;
                }
            }
            else if (paymentSystemTransactionId.equals(paymentSystemTransactionId2)) {
                break Label_0171;
            }
            return false;
        }
        final String comment = this.getComment();
        final String comment2 = payment.getComment();
        Label_0208: {
            if (comment == null) {
                if (comment2 == null) {
                    break Label_0208;
                }
            }
            else if (comment.equals(comment2)) {
                break Label_0208;
            }
            return false;
        }
        final PaymentStatus status = this.getStatus();
        final PaymentStatus status2 = payment.getStatus();
        if (status == null) {
            if (status2 == null) {
                return this.getCreatedDate() == payment.getCreatedDate() && this.getPaidDate() == payment.getPaidDate();
            }
        }
        else if (status.equals(status2)) {
            return this.getCreatedDate() == payment.getCreatedDate() && this.getPaidDate() == payment.getPaidDate();
        }
        return false;
    }

    protected boolean canEqual(final Object o) {
        return o instanceof Payment;
    }

    @Override
    public int hashCode() {
        final int n = 1;
        final long objectId = this.getObjectId();
        final int n2 = n * 59 + (int)(objectId >>> 32 ^ objectId);
        final String accountName = this.getAccountName();
        final int n3 = n2 * 59 + ((accountName == null) ? 43 : accountName.hashCode());
        //this.getSum();
        final long n4 =1L;
        final int n5 = n3 * 59 + (int)(n4 >>> 32 ^ n4);
        final String paymentSystemName = this.getPaymentSystemName();
        final int n6 = n5 * 59 + ((paymentSystemName == null) ? 43 : paymentSystemName.hashCode());
        final String paymentSystemTransactionId = this.getPaymentSystemTransactionId();
        final int n7 = n6 * 59 + ((paymentSystemTransactionId == null) ? 43 : paymentSystemTransactionId.hashCode());
        final String comment = this.getComment();
        final int n8 = n7 * 59 + ((comment == null) ? 43 : comment.hashCode());
        final PaymentStatus status = this.getStatus();
        final int n9 = n8 * 59 + ((status == null) ? 43 : status.hashCode());
        final long createdDate = this.getCreatedDate();
        final int n10 = n9 * 59 + (int)(createdDate >>> 32 ^ createdDate);
        final long paidDate = this.getPaidDate();
        return n10 * 59 + (int)(paidDate >>> 32 ^ paidDate);
    }
}
