package com.bdoemu.commons.xmlrpc.client;

/**
 * @ClassName XmlRpcTransportFactoryImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:37
 * VERSION 1.0
 */

public abstract class XmlRpcTransportFactoryImpl implements XmlRpcTransportFactory{

    private final XmlRpcClient client;

    protected XmlRpcTransportFactoryImpl(final XmlRpcClient pClient) {
        this.client = pClient;
    }

    public XmlRpcClient getClient() {
        return this.client;
    }
}
