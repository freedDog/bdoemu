package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName BigDecimalSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:20
 * VERSION 1.0
 */

public class BigDecimalSerializer extends TypeSerializerImpl{

    public static final String BIGDECIMAL_TAG = "bigdecimal";
    private static final String EX_BIGDECIMAL_TAG = "ex:bigdecimal";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "bigdecimal", "ex:bigdecimal", pObject.toString());
    }
}
