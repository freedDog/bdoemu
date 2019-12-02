package com.bdoemu.commons.xmlrpc.common;

/**
 * @ClassName XmlRpcRequest
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 12:02
 * VERSION 1.0
 */

public interface XmlRpcRequest {
    XmlRpcRequestConfig getConfig();

    String getMethodName();

    int getParameterCount();

    Object getParameter(int paramInt);
}
