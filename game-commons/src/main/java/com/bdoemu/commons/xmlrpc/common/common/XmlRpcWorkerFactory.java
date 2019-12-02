package com.bdoemu.commons.xmlrpc.common.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName XmlRpcWorkerFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:42
 * VERSION 1.0
 */

public abstract class XmlRpcWorkerFactory {
    private final XmlRpcWorker singleton;
    private final XmlRpcController controller;
    private final List<XmlRpcWorker> pool;
    private int numThreads;

    public XmlRpcWorkerFactory(final XmlRpcController pController) {
        this.singleton = this.newWorker();
        this.pool = new ArrayList();
        this.controller = pController;
    }

    protected abstract XmlRpcWorker newWorker();

    public XmlRpcController getController() {
        return this.controller;
    }

    public synchronized XmlRpcWorker getWorker() throws XmlRpcLoadException {
        final int max = this.controller.getMaxThreads();
        if (max > 0 && this.numThreads == max) {
            throw new XmlRpcLoadException("Maximum number of concurrent requests exceeded: " + max);
        }
        if (max == 0) {
            return this.singleton;
        }
        ++this.numThreads;
        if (this.pool.size() == 0) {
            return this.newWorker();
        }
        return this.pool.remove(this.pool.size() - 1);
    }

    public synchronized void releaseWorker(final XmlRpcWorker pWorker) {
        --this.numThreads;
        final int max = this.controller.getMaxThreads();
        if (pWorker != this.singleton) {
            if (this.pool.size() < max) {
                this.pool.add(pWorker);
            }
        }
    }

    public synchronized int getCurrentRequests() {
        return this.numThreads;
    }
}
