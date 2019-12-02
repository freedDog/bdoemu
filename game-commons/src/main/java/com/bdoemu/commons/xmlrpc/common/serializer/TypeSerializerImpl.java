package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @ClassName TypeSerializerImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:48
 * VERSION 1.0
 */

public abstract class TypeSerializerImpl implements TypeSerializer{

    protected static final Attributes ZERO_ATTRIBUTES;
    public static final String VALUE_TAG = "value";

    protected void write(final ContentHandler pHandler, final String pTagName, final String pValue) throws SAXException {
        this.write(pHandler, pTagName, pValue.toCharArray());
    }

    protected void write(final ContentHandler pHandler, final String pTagName, final char[] pValue) throws SAXException {
        pHandler.startElement("", "value", "value", TypeSerializerImpl.ZERO_ATTRIBUTES);
        if (pTagName != null) {
            pHandler.startElement("", pTagName, pTagName, TypeSerializerImpl.ZERO_ATTRIBUTES);
        }
        pHandler.characters(pValue, 0, pValue.length);
        if (pTagName != null) {
            pHandler.endElement("", pTagName, pTagName);
        }
        pHandler.endElement("", "value", "value");
    }

    protected void write(final ContentHandler pHandler, final String pLocalName, final String pQName, final String pValue) throws SAXException {
        pHandler.startElement("", "value", "value", TypeSerializerImpl.ZERO_ATTRIBUTES);
        pHandler.startElement("http://ws.apache.org/xmlrpc/namespaces/extensions", pLocalName, pQName, TypeSerializerImpl.ZERO_ATTRIBUTES);
        final char[] value = pValue.toCharArray();
        pHandler.characters(value, 0, value.length);
        pHandler.endElement("http://ws.apache.org/xmlrpc/namespaces/extensions", pLocalName, pQName);
        pHandler.endElement("", "value", "value");
    }

    static {
        ZERO_ATTRIBUTES = new AttributesImpl();
    }
}
