package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorker;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorkerFactory;

/**
 * @ClassName XmlRpcServerWorkerFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:17
 * VERSION 1.0
 */

public class XmlRpcServerWorkerFactory extends XmlRpcWorkerFactory {

    public XmlRpcServerWorkerFactory(XmlRpcServer pServer) { super(pServer); }

    @Override
    protected XmlRpcWorker newWorker() {
        return new XmlRpcServerWorker(this);
    }
}
