package com.bdoemu.core.xmlrpcservices;

import com.bdoemu.commons.model.enums.ELogEntryType;
import com.bdoemu.commons.model.xmlrpc.BaseXmlRpcHandler;
import com.bdoemu.commons.xmlrpc.XmlRpcHandler;

/**
 * @ClassName LogXmlRpcHandler
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:23
 * VERSION 1.0
 */

@XmlRpcHandler
public class LogXmlRpcHandler extends BaseXmlRpcHandler {

    public String create(final long n, final ELogEntryType eLogEntryType, final String s, final String s2) {
        return "";
    }

    public String getByAccountId(final long n) {
        return "";
    }

}
