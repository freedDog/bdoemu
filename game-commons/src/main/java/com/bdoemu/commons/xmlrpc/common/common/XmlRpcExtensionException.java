package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcExtensionException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:36
 * VERSION 1.0
 */

public class XmlRpcExtensionException extends XmlRpcException {

    private static final long serialVersionUID = 3617014169594311221L;

    public XmlRpcExtensionException(String pMessage) {
        super(0, pMessage);
    }
}
