package com.bdoemu.commons.xmlrpc.common.common;

/**
 * @ClassName XmlRpcHttpRequestConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:43
 * VERSION 1.0
 */

public interface XmlRpcHttpRequestConfig extends XmlRpcStreamRequestConfig,XmlRpcHttpConfig{

    String getBasicUserName();

    String getBasicPassword();

    int getConnectionTimeout();

    int getReplyTimeout();

}
