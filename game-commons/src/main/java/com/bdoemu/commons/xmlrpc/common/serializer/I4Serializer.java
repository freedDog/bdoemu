package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName I4Serializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:00
 * VERSION 1.0
 */

public class I4Serializer extends TypeSerializerImpl{

    public static final String INT_TAG = "int";
    public static final String I4_TAG = "i4";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "i4", pObject.toString());
    }
}
