package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @ClassName BooleanParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:51
 * VERSION 1.0
 */

public class BooleanParser extends AtomicParser{
    @Override
    protected void setResult(final String pResult) throws SAXException {
        final String s = pResult.trim();
        if ("1".equals(s)) {
            super.setResult(Boolean.TRUE);
        }
        else {
            if (!"0".equals(s)) {
                throw new SAXParseException("Failed to parse boolean value: " + pResult, this.getDocumentLocator());
            }
            super.setResult(Boolean.FALSE);
        }
    }
}
