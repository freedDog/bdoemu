package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName I2Serializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:03
 * VERSION 1.0
 */

public class I2Serializer extends TypeSerializerImpl{

    public static final String I2_TAG = "i2";
    public static final String EX_I2_TAG = "ex:i2";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "i2", "ex:i2", pObject.toString());
    }
}
