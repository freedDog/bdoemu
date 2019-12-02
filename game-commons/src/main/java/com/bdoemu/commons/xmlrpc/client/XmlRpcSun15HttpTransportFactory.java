package com.bdoemu.commons.xmlrpc.client;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @ClassName XmlRpcSun15HttpTransportFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:35
 * VERSION 1.0
 */

public class XmlRpcSun15HttpTransportFactory extends XmlRpcSun14HttpTransportFactory{

    private Proxy proxy;

    public XmlRpcSun15HttpTransportFactory(final XmlRpcClient pClient) {
        super(pClient);
    }

    public void setProxy(final String proxyHost, final int proxyPort) {
        this.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
    }

    public void setProxy(final Proxy pProxy) {
        this.proxy = pProxy;
    }

    @Override
    public XmlRpcTransport getTransport() {
        final XmlRpcSun15HttpTransport transport = new XmlRpcSun15HttpTransport(this.getClient());
        transport.setSSLSocketFactory(this.getSSLSocketFactory());
        transport.setProxy(this.proxy);
        return transport;
    }
}
