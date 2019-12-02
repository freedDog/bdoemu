package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcNotAuthorizedException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:23
 * VERSION 1.0
 */

public class XmlRpcNotAuthorizedException extends XmlRpcException {

    private static final long serialVersionUID = 3258410629709574201L;

    public XmlRpcNotAuthorizedException(String pMessage) {
        super(0, pMessage);
    }
}
