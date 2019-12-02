package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcHttpConfig;

/**
 * @ClassName XmlRpcHttpServerConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:11
 * VERSION 1.0
 */

public interface XmlRpcHttpServerConfig extends XmlRpcServerConfig, XmlRpcHttpConfig {

    boolean isKeepAliveEnabled();

    boolean isEnabledForExceptions();
}
