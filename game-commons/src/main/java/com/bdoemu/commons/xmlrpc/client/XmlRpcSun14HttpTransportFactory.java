package com.bdoemu.commons.xmlrpc.client;

import javax.net.ssl.SSLSocketFactory;

/**
 * @ClassName XmlRpcSun14HttpTransportFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:36
 * VERSION 1.0
 */

public class XmlRpcSun14HttpTransportFactory extends XmlRpcTransportFactoryImpl{

    private SSLSocketFactory sslSocketFactory;

    public XmlRpcSun14HttpTransportFactory(final XmlRpcClient pClient) {
        super(pClient);
    }

    public void setSSLSocketFactory(final SSLSocketFactory pSocketFactory) {
        this.sslSocketFactory = pSocketFactory;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.sslSocketFactory;
    }

    @Override
    public XmlRpcTransport getTransport() {
        final XmlRpcSun14HttpTransport transport = new XmlRpcSun14HttpTransport(this.getClient());
        transport.setSSLSocketFactory(this.sslSocketFactory);
        return transport;
    }
}
