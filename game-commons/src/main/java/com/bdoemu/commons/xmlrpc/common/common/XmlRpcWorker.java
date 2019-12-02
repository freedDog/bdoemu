package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;

/**
 * @ClassName XmlRpcWorker
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:44
 * VERSION 1.0
 */

public interface XmlRpcWorker {

    XmlRpcController getController();

    Object execute(final XmlRpcRequest p0) throws XmlRpcException;
}
