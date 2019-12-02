package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcHandler;
import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcController;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorker;

/**
 * @ClassName XmlRpcServerWorker
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:18
 * VERSION 1.0
 */

public class XmlRpcServerWorker implements XmlRpcWorker {

    private final XmlRpcServerWorkerFactory factory;

    public XmlRpcServerWorker(XmlRpcServerWorkerFactory pFactory) { this.factory = pFactory; }

    @Override
    public XmlRpcController getController() {
        return this.factory.getController();
    }

    @Override
    public Object execute(XmlRpcRequest pRequest) throws XmlRpcException {
        XmlRpcServer server = (XmlRpcServer)getController();
        XmlRpcHandlerMapping mapping = server.getHandlerMapping();
        XmlRpcHandler handler = mapping.getHandler(pRequest.getMethodName());
        return handler.execute(pRequest);
    }
}
