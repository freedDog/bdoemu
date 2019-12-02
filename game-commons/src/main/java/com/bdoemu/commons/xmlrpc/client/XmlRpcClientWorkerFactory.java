package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorker;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorkerFactory;

/**
 * @ClassName XmlRpcClientWorkerFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:26
 * VERSION 1.0
 */

public class XmlRpcClientWorkerFactory extends XmlRpcWorkerFactory {

    public XmlRpcClientWorkerFactory(final XmlRpcClient pClient) {
        super(pClient);
    }

    @Override
    protected XmlRpcWorker newWorker() {
        return new XmlRpcClientWorker(this);
    }
}
