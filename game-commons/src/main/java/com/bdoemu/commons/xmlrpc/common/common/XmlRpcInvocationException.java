package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcInvocationException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 21:04
 * VERSION 1.0
 */

public class XmlRpcInvocationException extends XmlRpcException {
    private static final long serialVersionUID = 7439737967784966169L;

    public XmlRpcInvocationException(final int pCode, final String pMessage, final Throwable pLinkedException) {
        super(pCode, pMessage, pLinkedException);
    }

    public XmlRpcInvocationException(final String pMessage, final Throwable pLinkedException) {
        super(pMessage, pLinkedException);
    }
}
