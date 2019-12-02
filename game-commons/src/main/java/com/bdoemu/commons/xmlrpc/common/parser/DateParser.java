package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.text.Format;
import java.text.ParseException;

/**
 * @ClassName DateParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:52
 * VERSION 1.0
 */

public class DateParser extends AtomicParser{

    private final Format f;

    public DateParser(final Format pFormat) {
        this.f = pFormat;
    }

    @Override
    protected void setResult(final String pResult) throws SAXException {
        final String s = pResult.trim();
        if (s.length() == 0) {
            return;
        }
        try {
            super.setResult(this.f.parseObject(s));
        }
        catch (ParseException e) {
            final int offset = e.getErrorOffset();
            String msg;
            if (e.getErrorOffset() == -1) {
                msg = "Failed to parse date value: " + pResult;
            }
            else {
                msg = "Failed to parse date value " + pResult + " at position " + offset;
            }
            throw new SAXParseException(msg, this.getDocumentLocator(), e);
        }
    }
}
