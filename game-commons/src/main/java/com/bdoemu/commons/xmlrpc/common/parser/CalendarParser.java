package com.bdoemu.commons.xmlrpc.common.parser;

import org.apache.ws.commons.util.XsDateTimeFormat;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.text.ParseException;

/**
 * @ClassName CalendarParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:48
 * VERSION 1.0
 */

public class CalendarParser extends AtomicParser{

    private static final XsDateTimeFormat format;

    @Override
    protected void setResult(final String pResult) throws SAXException {
        try {
            super.setResult(CalendarParser.format.parseObject(pResult.trim()));
        }
        catch (ParseException e) {
            final int offset = e.getErrorOffset();
            String msg;
            if (offset == -1) {
                msg = "Failed to parse dateTime value: " + pResult;
            }
            else {
                msg = "Failed to parse dateTime value " + pResult + " at position " + e.getErrorOffset();
            }
            throw new SAXParseException(msg, this.getDocumentLocator(), e);
        }
    }

    static {
        format = new XsDateTimeFormat();
    }
}
