package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcConfigImpl;

/**
 * @ClassName XmlRpcHttpRequestConfigImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:50
 * VERSION 1.0
 */

public class XmlRpcHttpRequestConfigImpl extends XmlRpcConfigImpl implements XmlRpcHttpRequestConfig {

    private boolean gzipCompressing;
    private boolean gzipRequesting;
    private String basicUserName;
    private String basicPassword;
    private int connectionTimeout;
    private int replyTimeout;
    private boolean enabledForExceptions;

    public XmlRpcHttpRequestConfigImpl() {
        this.connectionTimeout = 0;
        this.replyTimeout = 0;
    }

    public void setGzipCompressing(final boolean pCompressing) {
        this.gzipCompressing = pCompressing;
    }

    @Override
    public boolean isGzipCompressing() {
        return this.gzipCompressing;
    }

    public void setGzipRequesting(final boolean pRequesting) {
        this.gzipRequesting = pRequesting;
    }

    @Override
    public boolean isGzipRequesting() {
        return this.gzipRequesting;
    }

    public void setBasicUserName(final String pUser) {
        this.basicUserName = pUser;
    }

    @Override
    public String getBasicUserName() {
        return this.basicUserName;
    }

    public void setBasicPassword(final String pPassword) {
        this.basicPassword = pPassword;
    }

    @Override
    public String getBasicPassword() {
        return this.basicPassword;
    }

    public void setConnectionTimeout(final int pTimeout) {
        this.connectionTimeout = pTimeout;
    }

    @Override
    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setReplyTimeout(final int pTimeout) {
        this.replyTimeout = pTimeout;
    }

    @Override
    public int getReplyTimeout() {
        return this.replyTimeout;
    }

    public void setEnabledForExceptions(final boolean pEnabledForExceptions) {
        this.enabledForExceptions = pEnabledForExceptions;
    }

    @Override
    public boolean isEnabledForExceptions() {
        return this.enabledForExceptions;
    }
}
