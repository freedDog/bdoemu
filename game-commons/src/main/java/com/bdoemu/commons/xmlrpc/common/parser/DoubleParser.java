package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @ClassName DoubleParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:51
 * VERSION 1.0
 */

public class DoubleParser extends AtomicParser{

    @Override
    protected void setResult(final String pResult) throws SAXException {
        try {
            super.setResult(new Double(pResult));
        }
        catch (NumberFormatException e) {
            throw new SAXParseException("Failed to parse double value: " + pResult, this.getDocumentLocator());
        }
    }
}
