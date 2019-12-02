package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcHttpRequestConfigImpl;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcRequestProcessor;

import java.io.Serializable;
import java.net.URL;

/**
 * @ClassName XmlRpcClientConfigImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:13
 * VERSION 1.0
 */

public class XmlRpcClientConfigImpl extends XmlRpcHttpRequestConfigImpl implements XmlRpcHttpClientConfig,XmlRpcLocalClientConfig,Cloneable, Serializable {

    private static final long serialVersionUID = 4121131450507800889L;
    private URL serverURL;
    private XmlRpcRequestProcessor xmlRpcServer;
    private String userAgent;

    public XmlRpcClientConfigImpl cloneMe() {
        try {
            return (XmlRpcClientConfigImpl)this.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Unable to create my clone");
        }
    }

    public void setServerURL(final URL pURL) {
        this.serverURL = pURL;
    }

    @Override
    public URL getServerURL() {
        return this.serverURL;
    }

    public void setXmlRpcServer(final XmlRpcRequestProcessor pServer) {
        this.xmlRpcServer = pServer;
    }

    @Override
    public XmlRpcRequestProcessor getXmlRpcServer() {
        return this.xmlRpcServer;
    }

    @Override
    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(final String pUserAgent) {
        this.userAgent = pUserAgent;
    }
}
