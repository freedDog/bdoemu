package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcConfig;
import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.common.TypeConverterFactory;
import com.bdoemu.commons.xmlrpc.common.common.TypeConverterFactoryImpl;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcController;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcRequestProcessor;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorker;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorkerFactory;

/**
 * @ClassName XmlRpcServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:04
 * VERSION 1.0
 */

public class XmlRpcServer extends XmlRpcController implements XmlRpcRequestProcessor {

    private XmlRpcHandlerMapping handlerMapping;
    private TypeConverterFactory typeConverterFactory;
    private XmlRpcServerConfig config;

    public XmlRpcServer() {
        this.typeConverterFactory = new TypeConverterFactoryImpl();
        this.config = new XmlRpcServerConfigImpl();
    }

    @Override
    protected XmlRpcWorkerFactory getDefaultXmlRpcWorkerFactory() {
        return new XmlRpcServerWorkerFactory(this);
    }

    public void setTypeConverterFactory(final TypeConverterFactory pFactory) {
        this.typeConverterFactory = pFactory;
    }

    @Override
    public TypeConverterFactory getTypeConverterFactory() {
        return this.typeConverterFactory;
    }

    public void setConfig(final XmlRpcServerConfig pConfig) {
        this.config = pConfig;
    }

    @Override
    public XmlRpcConfig getConfig() {
        return this.config;
    }

    public void setHandlerMapping(final XmlRpcHandlerMapping pMapping) {
        this.handlerMapping = pMapping;
    }

    public XmlRpcHandlerMapping getHandlerMapping() {
        return this.handlerMapping;
    }

    @Override
    public Object execute(final XmlRpcRequest pRequest) throws XmlRpcException {
        final XmlRpcWorkerFactory factory = this.getWorkerFactory();
        final XmlRpcWorker worker = factory.getWorker();
        try {
            return worker.execute(pRequest);
        }
        finally {
            factory.releaseWorker(worker);
        }
    }
}
