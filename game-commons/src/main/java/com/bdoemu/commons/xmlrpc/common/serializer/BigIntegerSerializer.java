package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName BigIntegerSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:21
 * VERSION 1.0
 */

public class BigIntegerSerializer extends TypeSerializerImpl{

    public static final String BIGINTEGER_TAG = "biginteger";
    private static final String EX_BIGINTEGER_TAG = "ex:biginteger";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "biginteger", "ex:biginteger", pObject.toString());
    }
}
