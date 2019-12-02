package com.bdoemu.commons.xmlrpc.webserver;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcHttpRequestConfigImpl;

/**
 * @ClassName RequestData
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:11
 * VERSION 1.0
 */

public class RequestData extends XmlRpcHttpRequestConfigImpl {

    private final Connection connection;
    private boolean keepAlive;
    private String method;
    private String httpVersion;
    private int contentLength;
    private boolean success;

    public RequestData(final Connection pConnection) {
        this.contentLength = -1;
        this.connection = pConnection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public boolean isKeepAlive() {
        return this.keepAlive;
    }

    public void setKeepAlive(final boolean pKeepAlive) {
        this.keepAlive = pKeepAlive;
    }

    public String getHttpVersion() {
        return this.httpVersion;
    }

    public void setHttpVersion(final String pHttpVersion) {
        this.httpVersion = pHttpVersion;
    }

    public int getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(final int pContentLength) {
        this.contentLength = pContentLength;
    }

    public boolean isByteArrayRequired() {
        return this.isKeepAlive() || !this.isEnabledForExtensions() || !this.isContentLengthOptional();
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(final String pMethod) {
        this.method = pMethod;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(final boolean pSuccess) {
        this.success = pSuccess;
    }
}
