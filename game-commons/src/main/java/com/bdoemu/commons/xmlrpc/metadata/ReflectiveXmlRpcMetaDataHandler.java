package com.bdoemu.commons.xmlrpc.metadata;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.common.TypeConverterFactory;
import com.bdoemu.commons.xmlrpc.server.AbstractReflectiveHandlerMapping;
import com.bdoemu.commons.xmlrpc.server.ReflectiveXmlRpcHandler;
import com.bdoemu.commons.xmlrpc.server.RequestProcessorFactoryFactory;

import java.lang.reflect.Method;

/**
 * @ClassName ReflectiveXmlRpcMetaDataHandler
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 21:07
 * VERSION 1.0
 */

public class ReflectiveXmlRpcMetaDataHandler extends ReflectiveXmlRpcHandler implements XmlRpcMetaDataHandler {

    private final String[][] signatures;
    private final String methodHelp;

    public ReflectiveXmlRpcMetaDataHandler(final AbstractReflectiveHandlerMapping pMapping, final TypeConverterFactory pTypeConverterFactory, final Class pClass, final RequestProcessorFactoryFactory.RequestProcessorFactory pFactory, final Method[] pMethods, final String[][] pSignatures, final String pMethodHelp) {
        super(pMapping, pTypeConverterFactory, pClass, pFactory, pMethods);
        this.signatures = pSignatures;
        this.methodHelp = pMethodHelp;
    }

    @Override
    public String[][] getSignatures() throws XmlRpcException {
        return this.signatures;
    }

    @Override
    public String getMethodHelp() throws XmlRpcException {
        return this.methodHelp;
    }
}
