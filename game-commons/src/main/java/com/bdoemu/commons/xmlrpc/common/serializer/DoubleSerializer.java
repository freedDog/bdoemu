package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName DoubleSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:01
 * VERSION 1.0
 */

public class DoubleSerializer extends TypeSerializerImpl{

    public static final String DOUBLE_TAG = "double";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "double", pObject.toString());
    }
}
