package com.bdoemu.commons.xmlrpc.common;

import java.util.TimeZone;

/**
 * @ClassName XmlRpcConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 12:01
 * VERSION 1.0
 */

public interface XmlRpcConfig {
    boolean isEnabledForExtensions();

    TimeZone getTimeZone();
}
