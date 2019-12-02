package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcNoSuchHandlerException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:07
 * VERSION 1.0
 */

public class XmlRpcNoSuchHandlerException extends XmlRpcException {

    private static final long serialVersionUID = 3257002138218344501L;

    public XmlRpcNoSuchHandlerException(final String pMessage) {
        super(0, pMessage);
    }
}
