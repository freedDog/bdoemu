package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcRequestConfig;

/**
 * @ClassName XmlRpcStreamRequestConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:11
 * VERSION 1.0
 */

public interface XmlRpcStreamRequestConfig extends XmlRpcStreamConfig, XmlRpcRequestConfig {

    boolean isGzipCompressing();

    boolean isGzipRequesting();

    boolean isEnabledForExceptions();
}
