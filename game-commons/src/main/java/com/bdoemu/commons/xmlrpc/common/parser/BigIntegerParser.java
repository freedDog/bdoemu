package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.math.BigInteger;

/**
 * @ClassName BigIntegerParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:43
 * VERSION 1.0
 */

public class BigIntegerParser extends AtomicParser{
    @Override
    protected void setResult(final String pResult) throws SAXException {
        try {
            super.setResult(new BigInteger(pResult));
        }
        catch (NumberFormatException e) {
            throw new SAXParseException("Failed to parse BigInteger value: " + pResult, this.getDocumentLocator());
        }
    }
}
