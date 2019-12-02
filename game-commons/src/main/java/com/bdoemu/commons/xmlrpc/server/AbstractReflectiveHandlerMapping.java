package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcHandler;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.common.TypeConverterFactory;
import com.bdoemu.commons.xmlrpc.common.common.TypeConverterFactoryImpl;
import com.bdoemu.commons.xmlrpc.metadata.ReflectiveXmlRpcMetaDataHandler;
import com.bdoemu.commons.xmlrpc.metadata.Util;
import com.bdoemu.commons.xmlrpc.metadata.XmlRpcListableHandlerMapping;
import com.bdoemu.commons.xmlrpc.metadata.XmlRpcMetaDataHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AbstractReflectiveHandlerMapping
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:32
 * VERSION 1.0
 */

public class AbstractReflectiveHandlerMapping implements XmlRpcListableHandlerMapping {


    private TypeConverterFactory typeConverterFactory;
    protected Map<String,XmlRpcHandler> handlerMap;
    private AuthenticationHandler authenticationHandler;
    private RequestProcessorFactoryFactory requestProcessorFactoryFactory;
    private boolean voidMethodEnabled;

    public AbstractReflectiveHandlerMapping() {
        this.typeConverterFactory = new TypeConverterFactoryImpl();
        this.handlerMap = new HashMap();
        this.requestProcessorFactoryFactory = new RequestProcessorFactoryFactory.RequestSpecificProcessorFactoryFactory();
    }

    public void setTypeConverterFactory(final TypeConverterFactory pFactory) {
        this.typeConverterFactory = pFactory;
    }

    public TypeConverterFactory getTypeConverterFactory() {
        return this.typeConverterFactory;
    }

    public void setRequestProcessorFactoryFactory(final RequestProcessorFactoryFactory pFactory) {
        this.requestProcessorFactoryFactory = pFactory;
    }

    public RequestProcessorFactoryFactory getRequestProcessorFactoryFactory() {
        return this.requestProcessorFactoryFactory;
    }

    public AuthenticationHandler getAuthenticationHandler() {
        return this.authenticationHandler;
    }

    public void setAuthenticationHandler(final AuthenticationHandler pAuthenticationHandler) {
        this.authenticationHandler = pAuthenticationHandler;
    }

    protected boolean isHandlerMethod(final Method pMethod) {
        return Modifier.isPublic(pMethod.getModifiers()) && !Modifier.isStatic(pMethod.getModifiers()) && (this.isVoidMethodEnabled()
                || pMethod.getReturnType() != Void.TYPE) && pMethod.getDeclaringClass() != Object.class;
    }

    protected void registerPublicMethods(final String pKey, final Class pType) throws XmlRpcException {
        final Map<String,Method[]> map = new HashMap();
        final Method[] methods2;
        final Method[] methods = methods2 = pType.getMethods();
        for (final Method method : methods2) {
            if (this.isHandlerMethod(method)) {
                final String name = pKey + "." + method.getName();
                final Method[] oldMArray = map.get(name);
                Method[] mArray;
                if (oldMArray == null) {
                    mArray = new Method[] { method };
                }
                else {
                    mArray = new Method[oldMArray.length + 1];
                    System.arraycopy(oldMArray, 0, mArray, 0, oldMArray.length);
                    mArray[oldMArray.length] = method;
                }
                map.put(name, mArray);
            }
        }
        for (final Object o : map.entrySet()) {
            final Map.Entry<String,Method[]> entry = (Map.Entry)o;
            final String name2 = entry.getKey();
            final Method[] mArray2 = entry.getValue();
            this.handlerMap.put(name2, this.newXmlRpcHandler(pType, mArray2));
        }
    }

    protected XmlRpcHandler newXmlRpcHandler(final Class pClass, final Method[] pMethods) throws XmlRpcException {
        final String[][] sig = this.getSignature(pMethods);
        final String help = this.getMethodHelp(pClass, pMethods);
        final RequestProcessorFactoryFactory.RequestProcessorFactory factory = this.requestProcessorFactoryFactory.getRequestProcessorFactory(pClass);
        if (sig == null || help == null) {
            return new ReflectiveXmlRpcHandler(this, this.typeConverterFactory, pClass, factory, pMethods);
        }
        return new ReflectiveXmlRpcMetaDataHandler(this, this.typeConverterFactory, pClass, factory, pMethods, sig, help);
    }

    protected String[][] getSignature(final Method[] pMethods) {
        return Util.getSignature(pMethods);
    }

    protected String getMethodHelp(final Class pClass, final Method[] pMethods) {
        return Util.getMethodHelp(pClass, pMethods);
    }

    @Override
    public XmlRpcHandler getHandler(final String pHandlerName) throws XmlRpcException {
        final XmlRpcHandler result = this.handlerMap.get(pHandlerName);
        if (result == null) {
            throw new XmlRpcNoSuchHandlerException("No such handler: " + pHandlerName);
        }
        return result;
    }

    @Override
    public String[] getListMethods() throws XmlRpcException {
        final List<String> list = new ArrayList();
        for (final Map.Entry<String,XmlRpcHandler> o: this.handlerMap.entrySet()) {
            final Map.Entry<String,XmlRpcHandler> entry = o;
            if (entry.getValue() instanceof XmlRpcMetaDataHandler) {
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    @Override
    public String getMethodHelp(final String pHandlerName) throws XmlRpcException {
        final XmlRpcHandler h = this.getHandler(pHandlerName);
        if (h instanceof XmlRpcMetaDataHandler) {
            return ((XmlRpcMetaDataHandler)h).getMethodHelp();
        }
        throw new XmlRpcNoSuchHandlerException("No help available for method: " + pHandlerName);
    }

    @Override
    public String[][] getMethodSignature(final String pHandlerName) throws XmlRpcException {
        final XmlRpcHandler h = this.getHandler(pHandlerName);
        if (h instanceof XmlRpcMetaDataHandler) {
            return ((XmlRpcMetaDataHandler)h).getSignatures();
        }
        throw new XmlRpcNoSuchHandlerException("No metadata available for method: " + pHandlerName);
    }

    public boolean isVoidMethodEnabled() {
        return this.voidMethodEnabled;
    }

    public void setVoidMethodEnabled(final boolean pVoidMethodEnabled) {
        this.voidMethodEnabled = pVoidMethodEnabled;
    }

    public interface AuthenticationHandler
    {
        boolean isAuthorized(final XmlRpcRequest p0) throws XmlRpcException;
    }
}
