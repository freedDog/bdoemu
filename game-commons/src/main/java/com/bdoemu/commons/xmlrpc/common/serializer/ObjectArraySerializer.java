package com.bdoemu.commons.xmlrpc.common.serializer;

import com.bdoemu.commons.xmlrpc.common.common.TypeFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName ObjectArraySerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:00
 * VERSION 1.0
 */

public class ObjectArraySerializer extends TypeSerializerImpl{

    public static final String ARRAY_TAG = "array";
    public static final String DATA_TAG = "data";
    private final XmlRpcStreamConfig config;
    private final TypeFactory typeFactory;

    public ObjectArraySerializer(final TypeFactory pTypeFactory, final XmlRpcStreamConfig pConfig) {
        this.typeFactory = pTypeFactory;
        this.config = pConfig;
    }

    protected void writeObject(final ContentHandler pHandler, final Object pObject) throws SAXException {
        final TypeSerializer ts = this.typeFactory.getSerializer(this.config, pObject);
        if (ts == null) {
            throw new SAXException("Unsupported Java type: " + pObject.getClass().getName());
        }
        ts.write(pHandler, pObject);
    }

    protected void writeData(final ContentHandler pHandler, final Object pObject) throws SAXException {
        final Object[] data = (Object[])pObject;
        for (int i = 0; i < data.length; ++i) {
            this.writeObject(pHandler, data[i]);
        }
    }

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        pHandler.startElement("", "value", "value", ObjectArraySerializer.ZERO_ATTRIBUTES);
        pHandler.startElement("", "array", "array", ObjectArraySerializer.ZERO_ATTRIBUTES);
        pHandler.startElement("", "data", "data", ObjectArraySerializer.ZERO_ATTRIBUTES);
        this.writeData(pHandler, pObject);
        pHandler.endElement("", "data", "data");
        pHandler.endElement("", "array", "array");
        pHandler.endElement("", "value", "value");
    }
}
