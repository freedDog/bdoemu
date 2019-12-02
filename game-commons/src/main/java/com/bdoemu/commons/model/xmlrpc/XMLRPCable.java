package com.bdoemu.commons.model.xmlrpc;

/**
 * @ClassName XMLRPCable
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 19:58
 * VERSION 1.0
 */

public interface XMLRPCable {
    XmlRpcMessage toXMLRpcObject(String paramString);
}
