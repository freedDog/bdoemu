package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcHandler;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.common.TypeConverter;
import com.bdoemu.commons.xmlrpc.common.common.TypeConverterFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcInvocationException;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcNotAuthorizedException;
import com.bdoemu.commons.xmlrpc.metadata.Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectiveXmlRpcHandler
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:50
 * VERSION 1.0
 */

public class ReflectiveXmlRpcHandler implements XmlRpcHandler {

    private final AbstractReflectiveHandlerMapping mapping;
    private final MethodData[] methods;
    private final Class clazz;
    private final RequestProcessorFactoryFactory.RequestProcessorFactory requestProcessorFactory;

    public ReflectiveXmlRpcHandler(final AbstractReflectiveHandlerMapping pMapping, final TypeConverterFactory pTypeConverterFactory, final Class pClass, final RequestProcessorFactoryFactory.RequestProcessorFactory pFactory, final Method[] pMethods) {
        this.mapping = pMapping;
        this.clazz = pClass;
        this.methods = new MethodData[pMethods.length];
        this.requestProcessorFactory = pFactory;
        for (int i = 0; i < this.methods.length; ++i) {
            this.methods[i] = new MethodData(pMethods[i], pTypeConverterFactory);
        }
    }

    private Object getInstance(final XmlRpcRequest pRequest) throws XmlRpcException {
        return this.requestProcessorFactory.getRequestProcessor(pRequest);
    }

    @Override
    public Object execute(final XmlRpcRequest pRequest) throws XmlRpcException {
        final AbstractReflectiveHandlerMapping.AuthenticationHandler authHandler = this.mapping.getAuthenticationHandler();
        if (authHandler != null && !authHandler.isAuthorized(pRequest)) {
            throw new XmlRpcNotAuthorizedException("Not authorized");
        }
        final Object[] args = new Object[pRequest.getParameterCount()];
        for (int j = 0; j < args.length; ++j) {
            args[j] = pRequest.getParameter(j);
        }
        final Object instance = this.getInstance(pRequest);
        for (final MethodData methodData : this.methods) {
            final TypeConverter[] converters = methodData.typeConverters;
            if (args.length == converters.length) {
                boolean matching = true;
                for (int i = 0; i < args.length; ++i) {
                    if (!converters[i].isConvertable(args[i])) {
                        matching = false;
                        break;
                    }
                }
                if (matching) {
                    for (int i = 0; i < args.length; ++i) {
                        args[i] = converters[i].convert(args[i]);
                    }
                    return this.invoke(instance, methodData.method, args);
                }
            }
        }
        throw new XmlRpcException("No method matching arguments: " + Util.getSignature(args));
    }

    private Object invoke(final Object pInstance, final Method pMethod, final Object[] pArgs) throws XmlRpcException {
        try {
            return pMethod.invoke(pInstance, pArgs);
        }
        catch (IllegalAccessException e) {
            throw new XmlRpcException("Illegal access to method " + pMethod.getName() + " in class " + this.clazz.getName(), e);
        }
        catch (IllegalArgumentException e2) {
            throw new XmlRpcException("Illegal argument for method " + pMethod.getName() + " in class " + this.clazz.getName(), e2);
        }
        catch (InvocationTargetException e3) {
            final Throwable t = e3.getTargetException();
            if (t instanceof XmlRpcException) {
                throw (XmlRpcException)t;
            }
            throw new XmlRpcInvocationException("Failed to invoke method " + pMethod.getName() + " in class " + this.clazz.getName() + ": " + t.getMessage(), t);
        }
    }

    private static class MethodData
    {
        final Method method;
        final TypeConverter[] typeConverters;

        MethodData(final Method pMethod, final TypeConverterFactory pTypeConverterFactory) {
            this.method = pMethod;
            final Class[] paramClasses = this.method.getParameterTypes();
            this.typeConverters = new TypeConverter[paramClasses.length];
            for (int i = 0; i < paramClasses.length; ++i) {
                this.typeConverters[i] = pTypeConverterFactory.getTypeConverter(paramClasses[i]);
            }
        }
    }
}
