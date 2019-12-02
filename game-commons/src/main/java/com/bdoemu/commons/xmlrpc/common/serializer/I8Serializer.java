package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName I8Serializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:03
 * VERSION 1.0
 */

public class I8Serializer extends TypeSerializerImpl{

    public static final String I8_TAG = "i8";
    public static final String EX_I8_TAG = "ex:i8";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "i8", "ex:i8", pObject.toString());
    }
}
