package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @ClassName FloatParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:36
 * VERSION 1.0
 */

public class FloatParser extends AtomicParser{

    @Override
    protected void setResult(final String pResult) throws SAXException {
        try {
            super.setResult(new Float(pResult));
        }
        catch (NumberFormatException e) {
            throw new SAXParseException("Failed to parse float value: " + pResult, this.getDocumentLocator());
        }
    }
}
