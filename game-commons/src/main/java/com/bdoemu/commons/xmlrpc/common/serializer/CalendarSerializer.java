package com.bdoemu.commons.xmlrpc.common.serializer;

import org.apache.ws.commons.util.XsDateTimeFormat;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName CalendarSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:22
 * VERSION 1.0
 */

public class CalendarSerializer extends TypeSerializerImpl{

    private static final XsDateTimeFormat format;
    public static final String CALENDAR_TAG = "dateTime";
    private static final String EX_CALENDAR_TAG = "ex:dateTime";
    public static final String DATE_TAG = "dateTime.iso8601";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        this.write(pHandler, "dateTime", "ex:dateTime", CalendarSerializer.format.format(pObject));
    }

    static {
        format = new XsDateTimeFormat();
    }
}
