package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName BooleanSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:01
 * VERSION 1.0
 */

public class BooleanSerializer extends TypeSerializerImpl{

    public static final String BOOLEAN_TAG = "boolean";
    private static final char[] TRUE;
    private static final char[] FALSE;

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "boolean", ((boolean)pObject) ? BooleanSerializer.TRUE : BooleanSerializer.FALSE);
    }

    static {
        TRUE = new char[] { '1' };
        FALSE = new char[] { '0' };
    }
}
