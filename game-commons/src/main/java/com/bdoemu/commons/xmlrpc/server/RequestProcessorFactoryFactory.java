package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.metadata.Util;

/**
 * @ClassName RequestProcessorFactoryFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:37
 * VERSION 1.0
 */

public interface RequestProcessorFactoryFactory {

    RequestProcessorFactory getRequestProcessorFactory(final Class p0) throws XmlRpcException;

    public static class RequestSpecificProcessorFactoryFactory implements RequestProcessorFactoryFactory
    {
        protected Object getRequestProcessor(final Class pClass, final XmlRpcRequest pRequest) throws XmlRpcException {
            return Util.newInstance(pClass);
        }

        @Override
        public RequestProcessorFactory getRequestProcessorFactory(final Class pClass) throws XmlRpcException {
            return new RequestProcessorFactory() {
                @Override
                public Object getRequestProcessor(final XmlRpcRequest pRequest) throws XmlRpcException {
                    return RequestSpecificProcessorFactoryFactory.this.getRequestProcessor(pClass, pRequest);
                }
            };
        }
    }

    public static class StatelessProcessorFactoryFactory implements RequestProcessorFactoryFactory
    {
        protected Object getRequestProcessor(final Class pClass) throws XmlRpcException {
            return Util.newInstance(pClass);
        }

        @Override
        public RequestProcessorFactory getRequestProcessorFactory(final Class pClass) throws XmlRpcException {
            final Object processor = this.getRequestProcessor(pClass);
            return pRequest -> processor;
        }
    }

    public interface RequestProcessorFactory
    {
        Object getRequestProcessor(final XmlRpcRequest p0) throws XmlRpcException;
    }
}
