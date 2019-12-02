package com.bdoemu.commons.xmlrpc.client;

/**
 * @ClassName XmlRpcSunHttpTransportFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:13
 * VERSION 1.0
 */

public class XmlRpcSunHttpTransportFactory extends XmlRpcTransportFactoryImpl{

    public XmlRpcSunHttpTransportFactory(final XmlRpcClient pClient) {
        super(pClient);
    }

    @Override
    public XmlRpcTransport getTransport() {
        return new XmlRpcSunHttpTransport(this.getClient());
    }
}
