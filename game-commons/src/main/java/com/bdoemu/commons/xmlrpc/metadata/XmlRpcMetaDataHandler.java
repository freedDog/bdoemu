package com.bdoemu.commons.xmlrpc.metadata;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcHandler;

/**
 * @ClassName XmlRpcMetaDataHandler
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 21:09
 * VERSION 1.0
 */

public interface XmlRpcMetaDataHandler extends XmlRpcHandler {

    String[][] getSignatures() throws XmlRpcException;

    String getMethodHelp() throws XmlRpcException;
}
