package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcController;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcWorker;

/**
 * @ClassName XmlRpcClientWorker
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:27
 * VERSION 1.0
 */

public class XmlRpcClientWorker implements XmlRpcWorker {

    private final XmlRpcClientWorkerFactory factory;

    public XmlRpcClientWorker(final XmlRpcClientWorkerFactory pFactory) {
        this.factory = pFactory;
    }

    @Override
    public XmlRpcController getController() {
        return this.factory.getController();
    }

    @Override
    public Object execute(final XmlRpcRequest pRequest) throws XmlRpcException {
        try {
            final XmlRpcClient client = (XmlRpcClient)this.getController();
            return client.getTransportFactory().getTransport().sendRequest(pRequest);
        }
        finally {
            this.factory.releaseWorker(this);
        }
    }

    protected Thread newThread(final Runnable pRunnable) {
        final Thread result = new Thread(pRunnable);
        result.setDaemon(true);
        return result;
    }

    public void execute(final XmlRpcRequest pRequest, final AsyncCallback pCallback) {

        final Runnable runnable = () -> {
            Object result=null;
            Throwable th=null;
            XmlRpcClient client;
            try {
                client = (XmlRpcClient)this.getController();
                result = client.getTransportFactory().getTransport().sendRequest(pRequest);
            }
            catch (Throwable t) {
                th = t;
            }
            this.factory.releaseWorker(this);
            if (th == null) {
                pCallback.handleResult(pRequest, result);
            }
            else {
                pCallback.handleError(pRequest, th);
            }
            return;
        };
        this.newThread(runnable).start();
    }
}
