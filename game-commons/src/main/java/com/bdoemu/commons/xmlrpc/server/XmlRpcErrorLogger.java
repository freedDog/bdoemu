package com.bdoemu.commons.xmlrpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName XmlRpcErrorLogger
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 19:58
 * VERSION 1.0
 */

public class XmlRpcErrorLogger {
    private static final Logger log;

    public void log(final String pMessage, final Throwable pThrowable) {
        XmlRpcErrorLogger.log.error(pMessage, pThrowable);
    }

    public void log(final String pMessage) {
        XmlRpcErrorLogger.log.error(pMessage);
    }

    static {
        log = LoggerFactory.getLogger((Class)XmlRpcErrorLogger.class);
    }
}
