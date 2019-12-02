package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcLoadException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:23
 * VERSION 1.0
 */

public class XmlRpcLoadException extends XmlRpcException {

    private static final long serialVersionUID = 4050760511635272755L;

    public XmlRpcLoadException(final String pMessage) {
        super(0, pMessage, null);
    }
}
