package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcClientException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:07
 * VERSION 1.0
 */

public class XmlRpcClientException extends XmlRpcException {

    private static final long serialVersionUID = 3545798797134608691L;

    public XmlRpcClientException(final String pMessage, final Throwable pCause) {
        super(0, pMessage, pCause);
    }
}
