package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName StringSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:59
 * VERSION 1.0
 */

public class StringSerializer extends TypeSerializerImpl{

    public static final String STRING_TAG = "string";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, null, pObject.toString());
    }
}
