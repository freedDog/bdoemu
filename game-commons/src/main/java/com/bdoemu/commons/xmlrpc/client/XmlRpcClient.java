package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcConfig;
import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcController;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorkerFactory;
import com.bdoemu.commons.xmlrpc.common.serializer.XmlWriterFactory;

import java.util.List;

/**
 * @ClassName XmlRpcClient
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 11:59
 * VERSION 1.0
 */

public class XmlRpcClient extends XmlRpcController {
    private XmlRpcTransportFactory transportFactory;
    private XmlRpcClientConfig config;
    private XmlWriterFactory xmlWriterFactory;

    public XmlRpcClient(final XmlRpcClientConfig pConfig) {
        this.transportFactory = XmlRpcClientDefaults.newTransportFactory(this);
        this.config = XmlRpcClientDefaults.newXmlRpcClientConfig();
        this.xmlWriterFactory = XmlRpcClientDefaults.newXmlWriterFactory();
        this.config = pConfig;
    }

    @Override
    protected XmlRpcWorkerFactory getDefaultXmlRpcWorkerFactory() {
        return new XmlRpcClientWorkerFactory(this);
    }

    public void setConfig(final XmlRpcClientConfig pConfig) {
        this.config = pConfig;
    }

    @Override
    public XmlRpcConfig getConfig() {
        return this.config;
    }

    public XmlRpcClientConfig getClientConfig() {
        return this.config;
    }

    public void setTransportFactory(final XmlRpcTransportFactory pFactory) {
        this.transportFactory = pFactory;
    }

    public XmlRpcTransportFactory getTransportFactory() {
        return this.transportFactory;
    }

    public Object execute(final String pMethodName, final Object[] pParams) throws XmlRpcException {
        return this.execute(this.getClientConfig(), pMethodName, pParams);
    }

    public Object execute(final XmlRpcClientConfig pConfig, final String pMethodName, final Object[] pParams) throws XmlRpcException {
        return this.execute(new XmlRpcClientRequestImpl(pConfig, pMethodName, pParams));
    }

    public Object execute(final String pMethodName, final List pParams) throws XmlRpcException {
        return this.execute(this.getClientConfig(), pMethodName, pParams);
    }

    public Object execute(final XmlRpcClientConfig pConfig, final String pMethodName, final List pParams) throws XmlRpcException {
        return this.execute(new XmlRpcClientRequestImpl(pConfig, pMethodName, pParams));
    }

    public Object execute(final XmlRpcRequest pRequest) throws XmlRpcException {
        return this.getWorkerFactory().getWorker().execute(pRequest);
    }

    public void executeAsync(final String pMethodName, final Object[] pParams, final AsyncCallback pCallback) throws XmlRpcException {
        this.executeAsync(this.getClientConfig(), pMethodName, pParams, pCallback);
    }

    public void executeAsync(final XmlRpcClientConfig pConfig, final String pMethodName, final Object[] pParams, final AsyncCallback pCallback) throws XmlRpcException {
        this.executeAsync(new XmlRpcClientRequestImpl(pConfig, pMethodName, pParams), pCallback);
    }

    public void executeAsync(final String pMethodName, final List pParams, final AsyncCallback pCallback) throws XmlRpcException {
        this.executeAsync(this.getClientConfig(), pMethodName, pParams, pCallback);
    }

    public void executeAsync(final XmlRpcClientConfig pConfig, final String pMethodName, final List pParams, final AsyncCallback pCallback) throws XmlRpcException {
        this.executeAsync(new XmlRpcClientRequestImpl(pConfig, pMethodName, pParams), pCallback);
    }

    public void executeAsync(final XmlRpcRequest pRequest, final AsyncCallback pCallback) throws XmlRpcException {
        final XmlRpcClientWorker w = (XmlRpcClientWorker)this.getWorkerFactory().getWorker();
        w.execute(pRequest, pCallback);
    }

    public XmlWriterFactory getXmlWriterFactory() {
        return this.xmlWriterFactory;
    }

    public void setXmlWriterFactory(final XmlWriterFactory pFactory) {
        this.xmlWriterFactory = pFactory;
    }
}
