package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcConfig;

/**
 * @ClassName XmlRpcStreamConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 12:13
 * VERSION 1.0
 */

public interface XmlRpcStreamConfig extends XmlRpcConfig {

    public static final String UTF8_ENCODING = "UTF-8";

    String getEncoding();
}
