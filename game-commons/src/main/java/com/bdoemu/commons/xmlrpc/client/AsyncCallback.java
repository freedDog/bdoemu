package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;

/**
 * @ClassName AsyncCallback
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:28
 * VERSION 1.0
 */

public interface AsyncCallback {

    void handleResult(final XmlRpcRequest p0, final Object p1);

    void handleError(final XmlRpcRequest p0, final Throwable p1);
}
