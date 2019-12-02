package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcHandler;
import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcHandlerMapping
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:07
 * VERSION 1.0
 */

public interface XmlRpcHandlerMapping {

    XmlRpcHandler getHandler(String paramString) throws XmlRpcNoSuchHandlerException, XmlRpcException;
}
