package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcHttpTransportException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:09
 * VERSION 1.0
 */

public class XmlRpcHttpTransportException extends XmlRpcException {

    private static final long serialVersionUID = -6933992871198450027L;
    private final int status;
    private final String statusMessage;

    public XmlRpcHttpTransportException(final int pCode, final String pMessage) {
        this(pCode, pMessage, "HTTP server returned unexpected status: " + pMessage);
    }

    public XmlRpcHttpTransportException(final int httpStatusCode, final String httpStatusMessage, final String message) {
        super(message);
        this.status = httpStatusCode;
        this.statusMessage = httpStatusMessage;
    }

    public int getStatusCode() {
        return this.status;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }
}
