package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcConfig;

/**
 * @ClassName XmlRpcController
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:45
 * VERSION 1.0
 */

public abstract class XmlRpcController {

    private XmlRpcWorkerFactory workerFactory;
    private int maxThreads;
    private TypeFactory typeFactory;

    public XmlRpcController() {
        this.workerFactory = this.getDefaultXmlRpcWorkerFactory();
        this.typeFactory = new TypeFactoryImpl(this);
    }

    protected abstract XmlRpcWorkerFactory getDefaultXmlRpcWorkerFactory();

    public void setMaxThreads(final int pMaxThreads) {
        this.maxThreads = pMaxThreads;
    }

    public int getMaxThreads() {
        return this.maxThreads;
    }

    public void setWorkerFactory(final XmlRpcWorkerFactory pFactory) {
        this.workerFactory = pFactory;
    }

    public XmlRpcWorkerFactory getWorkerFactory() {
        return this.workerFactory;
    }

    public abstract XmlRpcConfig getConfig();

    public void setTypeFactory(final TypeFactory pTypeFactory) {
        this.typeFactory = pTypeFactory;
    }

    public TypeFactory getTypeFactory() {
        return this.typeFactory;
    }
}
