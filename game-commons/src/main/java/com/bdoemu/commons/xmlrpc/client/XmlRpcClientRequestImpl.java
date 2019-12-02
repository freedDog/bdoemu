package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequestConfig;

import java.util.List;

/**
 * @ClassName XmlRpcClientRequestImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:33
 * VERSION 1.0
 */

public class XmlRpcClientRequestImpl implements XmlRpcRequest {

    private static final Object[] ZERO_PARAMS;
    private final XmlRpcRequestConfig config;
    private final String methodName;
    private final Object[] params;

    public XmlRpcClientRequestImpl(final XmlRpcRequestConfig pConfig, final String pMethodName, final Object[] pParams) {
        this.config = pConfig;
        if (this.config == null) {
            throw new NullPointerException("The request configuration must not be null.");
        }
        this.methodName = pMethodName;
        if (this.methodName == null) {
            throw new NullPointerException("The method name must not be null.");
        }
        this.params = ((pParams == null) ? XmlRpcClientRequestImpl.ZERO_PARAMS : pParams);
    }

    public XmlRpcClientRequestImpl(final XmlRpcRequestConfig pConfig, final String pMethodName, final List pParams) {
        this(pConfig, pMethodName, (Object[])((pParams == null) ? null : pParams.toArray()));
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    public int getParameterCount() {
        return this.params.length;
    }

    @Override
    public Object getParameter(final int pIndex) {
        return this.params[pIndex];
    }

    @Override
    public XmlRpcRequestConfig getConfig() {
        return this.config;
    }

    static {
        ZERO_PARAMS = new Object[0];
    }
}
