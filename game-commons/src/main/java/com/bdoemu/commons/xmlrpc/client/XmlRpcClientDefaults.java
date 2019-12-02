package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.serializer.DefaultXMLWriterFactory;
import com.bdoemu.commons.xmlrpc.common.serializer.XmlWriterFactory;

/**
 * @ClassName XmlRpcClientDefaults
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 12:15
 * VERSION 1.0
 */

public class XmlRpcClientDefaults {
    private static final XmlWriterFactory xmlWriterFactory;

    public static XmlRpcTransportFactory newTransportFactory(final XmlRpcClient pClient) {
        try {
            return new XmlRpcSun15HttpTransportFactory(pClient);
        }
        catch (Throwable t1) {
            try {
                return new XmlRpcSun14HttpTransportFactory(pClient);
            }
            catch (Throwable t2) {
                return new XmlRpcSunHttpTransportFactory(pClient);
            }
        }
    }

    public static XmlRpcClientConfig newXmlRpcClientConfig() {
        return new XmlRpcClientConfigImpl();
    }

    public static XmlWriterFactory newXmlWriterFactory() {
        return XmlRpcClientDefaults.xmlWriterFactory;
    }

    static {
        xmlWriterFactory = new DefaultXMLWriterFactory();
    }
}
