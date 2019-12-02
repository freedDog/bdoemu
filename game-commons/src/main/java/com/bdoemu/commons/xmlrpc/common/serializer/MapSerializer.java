package com.bdoemu.commons.xmlrpc.common.serializer;

import com.bdoemu.commons.xmlrpc.common.common.TypeFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.util.Map;

/**
 * @ClassName MapSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:02
 * VERSION 1.0
 */

public class MapSerializer extends TypeSerializerImpl{

    public static final String STRUCT_TAG = "struct";
    public static final String MEMBER_TAG = "member";
    public static final String NAME_TAG = "name";
    private final XmlRpcStreamConfig config;
    private final TypeFactory typeFactory;

    public MapSerializer(final TypeFactory pTypeFactory, final XmlRpcStreamConfig pConfig) {
        this.typeFactory = pTypeFactory;
        this.config = pConfig;
    }

    protected void writeEntry(final ContentHandler pHandler, final Object pKey, final Object pValue) throws SAXException {
        pHandler.startElement("", "member", "member", MapSerializer.ZERO_ATTRIBUTES);
        pHandler.startElement("", "name", "name", MapSerializer.ZERO_ATTRIBUTES);
        if (this.config.isEnabledForExtensions() && !(pKey instanceof String)) {
            this.writeValue(pHandler, pKey);
        }
        else {
            final String key = pKey.toString();
            pHandler.characters(key.toCharArray(), 0, key.length());
        }
        pHandler.endElement("", "name", "name");
        this.writeValue(pHandler, pValue);
        pHandler.endElement("", "member", "member");
    }

    private void writeValue(final ContentHandler pHandler, final Object pValue) throws SAXException {
        final TypeSerializer ts = this.typeFactory.getSerializer(this.config, pValue);
        if (ts == null) {
            throw new SAXException("Unsupported Java type: " + pValue.getClass().getName());
        }
        ts.write(pHandler, pValue);
    }

    protected void writeData(final ContentHandler pHandler, final Object pData) throws SAXException {
        final Map<Object,Object> map = (Map)pData;
        for (final Map.Entry entry : map.entrySet()) {
            this.writeEntry(pHandler, entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        pHandler.startElement("", "value", "value", MapSerializer.ZERO_ATTRIBUTES);
        pHandler.startElement("", "struct", "struct", MapSerializer.ZERO_ATTRIBUTES);
        this.writeData(pHandler, pObject);
        pHandler.endElement("", "struct", "struct");
        pHandler.endElement("", "value", "value");
    }
}
