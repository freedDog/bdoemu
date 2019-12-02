package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName PropertyHandlerMapping
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:32
 * VERSION 1.0
 */

public class PropertyHandlerMapping extends AbstractReflectiveHandlerMapping{

    public void load(final ClassLoader pClassLoader, final String pResource) throws IOException, XmlRpcException {
        final URL url = pClassLoader.getResource(pResource);
        if (url == null) {
            throw new IOException("Unable to locate resource " + pResource);
        }
        this.load(pClassLoader, url);
    }

    public void load(final ClassLoader pClassLoader, final URL pURL) throws IOException, XmlRpcException {
        final Properties props = new Properties();
        props.load(pURL.openStream());
        this.load(pClassLoader, props);
    }

    public void load(final ClassLoader pClassLoader, final Map pMap) throws XmlRpcException {
        for (Object o : pMap.entrySet()) {
            Map.Entry entry = (Map.Entry)o;
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            Class c = newHandlerClass(pClassLoader, value);
            registerPublicMethods(key, c);
        }
    }

    protected Class newHandlerClass(final ClassLoader pClassLoader, final String pClassName) throws XmlRpcException {
        Class c;
        try {
            c = pClassLoader.loadClass(pClassName);
        }
        catch (ClassNotFoundException e) {
            throw new XmlRpcException("Unable to load class: " + pClassName, e);
        }
        if (c == null) {
            throw new XmlRpcException(0, "Loading class " + pClassName + " returned null.");
        }
        return c;
    }

    public void addHandler(final String pKey, final Class pClass) throws XmlRpcException {
        this.registerPublicMethods(pKey, pClass);
    }

    public void removeHandler(final String pKey) {
        final Iterator<String> i = this.handlerMap.keySet().iterator();
        while (i.hasNext()) {
            final String k = i.next();
            if (k.startsWith(pKey)) {
                i.remove();
            }
        }
    }
}
