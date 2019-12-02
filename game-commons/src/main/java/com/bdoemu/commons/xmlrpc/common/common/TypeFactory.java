package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.parser.TypeParser;
import com.bdoemu.commons.xmlrpc.common.serializer.TypeSerializer;
import org.apache.ws.commons.util.NamespaceContextImpl;
import org.xml.sax.SAXException;

/**
 * @ClassName TypeFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:27
 * VERSION 1.0
 */

public interface TypeFactory {

    TypeSerializer getSerializer(XmlRpcStreamConfig paramXmlRpcStreamConfig, Object paramObject) throws SAXException;

    TypeParser getParser(XmlRpcStreamConfig paramXmlRpcStreamConfig, NamespaceContextImpl paramNamespaceContextImpl, String paramString1, String paramString2);
}
