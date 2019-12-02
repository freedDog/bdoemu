package com.bdoemu.commons.xmlrpc.common.serializer;

import com.bdoemu.commons.xmlrpc.common.common.TypeFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.util.List;

/**
 * @ClassName ListSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:01
 * VERSION 1.0
 */

public class ListSerializer extends ObjectArraySerializer{

    public ListSerializer(final TypeFactory pTypeFactory, final XmlRpcStreamConfig pConfig) {
        super(pTypeFactory, pConfig);
    }

    @Override
    protected void writeData(final ContentHandler pHandler, final Object pObject) throws SAXException {
        final List data = (List)pObject;
        for (final Object aData : data) {
            this.writeObject(pHandler, aData);
        }
    }
}
