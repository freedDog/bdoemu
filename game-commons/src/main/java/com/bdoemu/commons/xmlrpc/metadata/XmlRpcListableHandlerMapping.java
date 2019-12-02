package com.bdoemu.commons.xmlrpc.metadata;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.server.XmlRpcHandlerMapping;

/**
 * @ClassName XmlRpcListableHandlerMapping
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:33
 * VERSION 1.0
 */

public interface XmlRpcListableHandlerMapping extends XmlRpcHandlerMapping {

    String[] getListMethods() throws XmlRpcException;

    String[][] getMethodSignature(String paramString) throws XmlRpcException;

    String getMethodHelp(String paramString) throws XmlRpcException;
}
