package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName FloatSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:04
 * VERSION 1.0
 */

public class FloatSerializer extends TypeSerializerImpl{

    public static final String FLOAT_TAG = "float";
    public static final String EX_FLOAT_TAG = "ex:float";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "float", "ex:float", pObject.toString());
    }
}
