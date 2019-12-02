package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName ExtSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:17
 * VERSION 1.0
 */

public abstract class ExtSerializer implements TypeSerializer{
    protected abstract String getTagName();

    protected abstract void serialize(final ContentHandler p0, final Object p1) throws SAXException;

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        final String tag = this.getTagName();
        final String exTag = "ex:" + this.getTagName();
        pHandler.startElement("", "value", "value", TypeSerializerImpl.ZERO_ATTRIBUTES);
        pHandler.startElement("http://ws.apache.org/xmlrpc/namespaces/extensions", tag, exTag, TypeSerializerImpl.ZERO_ATTRIBUTES);
        this.serialize(pHandler, pObject);
        pHandler.endElement("http://ws.apache.org/xmlrpc/namespaces/extensions", tag, exTag);
        pHandler.endElement("", "value", "value");
    }
}
