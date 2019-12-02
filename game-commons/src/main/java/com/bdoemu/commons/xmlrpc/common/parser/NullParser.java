package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @ClassName NullParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:17
 * VERSION 1.0
 */

public class NullParser extends AtomicParser{


    @Override
    protected void setResult(String pResult) throws SAXException {
        if (pResult == null || "".equals(pResult.trim())) {
            super.setResult((Object) null);
            return;
        }
        throw new SAXParseException("Unexpected characters in nil element.", this.getDocumentLocator());
    }
}
