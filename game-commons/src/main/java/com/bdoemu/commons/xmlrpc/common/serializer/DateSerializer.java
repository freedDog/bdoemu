package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.text.Format;

/**
 * @ClassName DateSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:48
 * VERSION 1.0
 */

public class DateSerializer extends TypeSerializerImpl{
    public static final String DATE_TAG = "dateTime.iso8601";
    private final Format format;

    public DateSerializer(final Format pFormat) {
        this.format = pFormat;
    }

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "dateTime.iso8601", this.format.format(pObject));
    }
}
