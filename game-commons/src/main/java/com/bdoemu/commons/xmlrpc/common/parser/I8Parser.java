package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @ClassName I8Parser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:33
 * VERSION 1.0
 */

public class I8Parser extends AtomicParser{

    @Override
    protected void setResult(final String pResult) throws SAXException {
        try {
            super.setResult(new Long(pResult.trim()));
        }
        catch (NumberFormatException e) {
            throw new SAXParseException("Failed to parse long value: " + pResult, this.getDocumentLocator());
        }
    }
}
