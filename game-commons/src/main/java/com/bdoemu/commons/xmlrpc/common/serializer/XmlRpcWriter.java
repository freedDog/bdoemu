package com.bdoemu.commons.xmlrpc.common.serializer;

import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequestConfig;
import com.bdoemu.commons.xmlrpc.common.common.TypeFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamRequestConfig;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName XmlRpcWriter
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:57
 * VERSION 1.0
 */

public class XmlRpcWriter {

    public static final String EXTENSIONS_URI = "http://ws.apache.org/xmlrpc/namespaces/extensions";
    private static final Attributes ZERO_ATTRIBUTES;
    private final XmlRpcStreamConfig config;
    private final TypeFactory typeFactory;
    private final ContentHandler handler;

    public XmlRpcWriter(final XmlRpcStreamConfig pConfig, final ContentHandler pHandler, final TypeFactory pTypeFactory) {
        this.config = pConfig;
        this.handler = pHandler;
        this.typeFactory = pTypeFactory;
    }

    public void write(final XmlRpcRequest pRequest) throws SAXException {
        this.handler.startDocument();
        final boolean extensions = pRequest.getConfig().isEnabledForExtensions();
        if (extensions) {
            this.handler.startPrefixMapping("ex", "http://ws.apache.org/xmlrpc/namespaces/extensions");
        }
        this.handler.startElement("", "methodCall", "methodCall", XmlRpcWriter.ZERO_ATTRIBUTES);
        this.handler.startElement("", "methodName", "methodName", XmlRpcWriter.ZERO_ATTRIBUTES);
        final String s = pRequest.getMethodName();
        this.handler.characters(s.toCharArray(), 0, s.length());
        this.handler.endElement("", "methodName", "methodName");
        this.handler.startElement("", "params", "params", XmlRpcWriter.ZERO_ATTRIBUTES);
        for (int num = pRequest.getParameterCount(), i = 0; i < num; ++i) {
            this.handler.startElement("", "param", "param", XmlRpcWriter.ZERO_ATTRIBUTES);
            this.writeValue(pRequest.getParameter(i));
            this.handler.endElement("", "param", "param");
        }
        this.handler.endElement("", "params", "params");
        this.handler.endElement("", "methodCall", "methodCall");
        if (extensions) {
            this.handler.endPrefixMapping("ex");
        }
        this.handler.endDocument();
    }

    public void write(final XmlRpcRequestConfig pConfig, final Object pResult) throws SAXException {
        this.handler.startDocument();
        final boolean extensions = pConfig.isEnabledForExtensions();
        if (extensions) {
            this.handler.startPrefixMapping("ex", "http://ws.apache.org/xmlrpc/namespaces/extensions");
        }
        this.handler.startElement("", "methodResponse", "methodResponse", XmlRpcWriter.ZERO_ATTRIBUTES);
        this.handler.startElement("", "params", "params", XmlRpcWriter.ZERO_ATTRIBUTES);
        this.handler.startElement("", "param", "param", XmlRpcWriter.ZERO_ATTRIBUTES);
        this.writeValue(pResult);
        this.handler.endElement("", "param", "param");
        this.handler.endElement("", "params", "params");
        this.handler.endElement("", "methodResponse", "methodResponse");
        if (extensions) {
            this.handler.endPrefixMapping("ex");
        }
        this.handler.endDocument();
    }

    public void write(final XmlRpcRequestConfig pConfig, final int pCode, final String pMessage) throws SAXException {
        this.write(pConfig, pCode, pMessage, null);
    }

    public void write(final XmlRpcRequestConfig pConfig, final int pCode, final String pMessage, final Throwable pThrowable) throws SAXException {
        this.handler.startDocument();
        final boolean extensions = pConfig.isEnabledForExtensions();
        if (extensions) {
            this.handler.startPrefixMapping("ex", "http://ws.apache.org/xmlrpc/namespaces/extensions");
        }
        this.handler.startElement("", "methodResponse", "methodResponse", XmlRpcWriter.ZERO_ATTRIBUTES);
        this.handler.startElement("", "fault", "fault", XmlRpcWriter.ZERO_ATTRIBUTES);
        final Map map = new HashMap();
        map.put("faultCode", new Integer(pCode));
        map.put("faultString", (pMessage == null) ? "" : pMessage);
        if (pThrowable != null && extensions && pConfig instanceof XmlRpcStreamRequestConfig && ((XmlRpcStreamRequestConfig)pConfig).isEnabledForExceptions()) {
            try {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(pThrowable);
                oos.close();
                baos.close();
                map.put("faultCause", baos.toByteArray());
            }
            catch (Throwable t) {}
        }
        this.writeValue(map);
        this.handler.endElement("", "fault", "fault");
        this.handler.endElement("", "methodResponse", "methodResponse");
        if (extensions) {
            this.handler.endPrefixMapping("ex");
        }
        this.handler.endDocument();
    }

    protected void writeValue(final Object pObject) throws SAXException {
        final TypeSerializer serializer = this.typeFactory.getSerializer(this.config, pObject);
        if (serializer == null) {
            throw new SAXException("Unsupported Java type: " + pObject.getClass().getName());
        }
        serializer.write(this.handler, pObject);
    }

    static {
        ZERO_ATTRIBUTES = new AttributesImpl();
    }
}
