package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcHttpRequestConfig;

import java.net.URL;

/**
 * @ClassName XmlRpcHttpClientConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:43
 * VERSION 1.0
 */

public interface XmlRpcHttpClientConfig extends XmlRpcHttpRequestConfig {

    URL getServerURL();

    String getUserAgent();
}
