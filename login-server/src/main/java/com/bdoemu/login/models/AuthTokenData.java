package com.bdoemu.login.models;

import com.bdoemu.commons.model.xmlrpc.XMLRPCable;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcAuthTokenData;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @ClassName AuthTokenData
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:35
 * VERSION 1.0
 */

public class AuthTokenData implements XMLRPCable {
    private String accountName;
    private String key;
    private long expireDate;

    public void generateToken() {
        this.key= BCrypt.hashpw(this.accountName,this.hashCode()+"");
    }
    @Override
    public XmlRpcAuthTokenData toXMLRpcObject(final String s) {
        final XmlRpcAuthTokenData xmlRpcAuthTokenData = new XmlRpcAuthTokenData();
        xmlRpcAuthTokenData.setAccountName(this.accountName);
        xmlRpcAuthTokenData.setKey(this.key);
        xmlRpcAuthTokenData.setExpireDate(this.expireDate);
        return xmlRpcAuthTokenData;
    }

    @Override
    public String toString() {
        return this.key;
    }

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
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuthTokenData)) {
            return false;
        }
        final AuthTokenData authTokenData = (AuthTokenData)o;
        if (!authTokenData.canEqual(this)) {
            return false;
        }
        final String key = this.getKey();
        final String key2 = authTokenData.getKey();
        if (key == null) {
            if (key2 == null) {
                return this.getExpireDate() == authTokenData.getExpireDate();
            }
        }
        else if (key.equals(key2)) {
            return this.getExpireDate() == authTokenData.getExpireDate();
        }
        return false;
    }

    protected boolean canEqual(final Object o) {
        return o instanceof AuthTokenData;
    }

    @Override
    public int hashCode() {
        final int n = 1;
        final String accountName = this.getAccountName();
        final int n2 = n * 59 + ((accountName == null) ? 43 : accountName.hashCode());
        final String key = this.getKey();
        final int n3 = n2 * 59 + ((key == null) ? 43 : key.hashCode());
        final long expireDate = this.getExpireDate();
        return n3 * 59 + (int)(expireDate >>> 32 ^ expireDate);
    }

}
