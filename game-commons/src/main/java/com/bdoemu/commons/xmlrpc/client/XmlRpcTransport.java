package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;

/**
 * @ClassName XmlRpcTransport
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 12:05
 * VERSION 1.0
 */

public interface XmlRpcTransport {

    Object sendRequest(XmlRpcRequest paramXmlRpcRequest) throws XmlRpcException;
}
