package com.bdoemu.commons.xmlrpc.client;

/**
 * @ClassName XmlRpcTransportImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:04
 * VERSION 1.0
 */


public abstract class XmlRpcTransportImpl implements XmlRpcTransport{
    private final XmlRpcClient client;

    protected XmlRpcTransportImpl(final XmlRpcClient pClient) {
        this.client = pClient;
    }

    public XmlRpcClient getClient() {
        return this.client;
    }
}
