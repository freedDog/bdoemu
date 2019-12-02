package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName I1Serializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:02
 * VERSION 1.0
 */

public class I1Serializer extends TypeSerializerImpl{

    public static final String I1_TAG = "i1";
    public static final String EX_I1_TAG = "ex:i1";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "i1", "ex:i1", pObject.toString());
    }
}
