package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @ClassName I4Parser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:50
 * VERSION 1.0
 */

public class I4Parser extends AtomicParser{
    @Override
    protected void setResult(final String pResult) throws SAXException {
        try {
            super.setResult(new Integer(pResult.trim()));
        }
        catch (NumberFormatException e) {
            throw new SAXParseException("Failed to parse integer value: " + pResult, this.getDocumentLocator());
        }
    }
}
