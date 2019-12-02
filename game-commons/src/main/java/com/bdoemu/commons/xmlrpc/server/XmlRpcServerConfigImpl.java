package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcConfigImpl;

/**
 * @ClassName XmlRpcServerConfigImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:10
 * VERSION 1.0
 */

public class XmlRpcServerConfigImpl extends XmlRpcConfigImpl implements XmlRpcServerConfig,XmlRpcHttpServerConfig {

    private boolean isKeepAliveEnabled;
    private boolean isEnabledForExceptions;

    public void setKeepAliveEnabled(final boolean pKeepAliveEnabled) {
        this.isKeepAliveEnabled = pKeepAliveEnabled;
    }

    @Override
    public boolean isKeepAliveEnabled() {
        return this.isKeepAliveEnabled;
    }

    public void setEnabledForExceptions(final boolean pEnabledForExceptions) {
        this.isEnabledForExceptions = pEnabledForExceptions;
    }

    @Override
    public boolean isEnabledForExceptions() {
        return this.isEnabledForExceptions;
    }
}
