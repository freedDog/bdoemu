package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

/**
 * @ClassName XmlRpcStreamRequestProcessor
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:24
 * VERSION 1.0
 */

public interface XmlRpcStreamRequestProcessor extends XmlRpcRequestProcessor{

    void execute(XmlRpcStreamRequestConfig paramXmlRpcStreamRequestConfig, ServerStreamConnection paramServerStreamConnection) throws XmlRpcException;
}
