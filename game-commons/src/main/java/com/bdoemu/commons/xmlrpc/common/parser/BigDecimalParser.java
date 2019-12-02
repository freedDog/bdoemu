package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.math.BigDecimal;

/**
 * @ClassName BigDecimalParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:42
 * VERSION 1.0
 */

public class BigDecimalParser extends AtomicParser{

    @Override
    protected void setResult(final String pResult) throws SAXException {
        try {
            super.setResult(new BigDecimal(pResult));
        }
        catch (NumberFormatException e) {
            throw new SAXParseException("Failed to parse BigDecimal value: " + pResult, this.getDocumentLocator());
        }
    }
}
