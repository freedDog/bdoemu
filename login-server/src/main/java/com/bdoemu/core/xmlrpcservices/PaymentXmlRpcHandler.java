package com.bdoemu.core.xmlrpcservices;

import com.bdoemu.commons.model.xmlrpc.BaseXmlRpcHandler;
import com.bdoemu.commons.xmlrpc.XmlRpcHandler;

/**
 * @ClassName PaymentXmlRpcHandler
 * @Description 支付Xml Rpc处理程序
 * @Author JiangBangMing
 * @Date 2019/6/26 18:24
 * VERSION 1.0
 */

@XmlRpcHandler
public class PaymentXmlRpcHandler extends BaseXmlRpcHandler {

    public String create(final String s, final Double n, final String s2) {
        return "";
    }

    public String get(final long n) {
        return "";
    }

    public String error(final long n, final String s, final String s2) {
        return "";
    }

    public String getByAccount(final String s) {
        return "";
    }
}
