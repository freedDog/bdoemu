package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName NullSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:58
 * VERSION 1.0
 */

public class NullSerializer extends TypeSerializerImpl{

    public static final String NIL_TAG = "nil";
    public static final String EX_NIL_TAG = "ex:nil";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        pHandler.startElement("", "value", "value", NullSerializer.ZERO_ATTRIBUTES);
        pHandler.startElement("http://ws.apache.org/xmlrpc/namespaces/extensions", "nil", "ex:nil", NullSerializer.ZERO_ATTRIBUTES);
        pHandler.endElement("http://ws.apache.org/xmlrpc/namespaces/extensions", "nil", "ex:nil");
        pHandler.endElement("", "value", "value");
    }
}
