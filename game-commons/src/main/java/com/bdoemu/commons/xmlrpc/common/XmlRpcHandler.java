package com.bdoemu.commons.xmlrpc.common;

/**
 * @ClassName XmlRpcHandler
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:20
 * VERSION 1.0
 */

public interface XmlRpcHandler {

    Object execute(XmlRpcRequest paramXmlRpcRequest) throws XmlRpcException;
}
