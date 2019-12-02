package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;

/**
 * @ClassName XmlRpcRequestProcessor
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:16
 * VERSION 1.0
 */

public interface XmlRpcRequestProcessor {

    Object execute(final XmlRpcRequest p0) throws XmlRpcException;

    TypeConverterFactory getTypeConverterFactory();
}
