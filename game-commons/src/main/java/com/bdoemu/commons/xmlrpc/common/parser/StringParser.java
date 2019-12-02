package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.SAXException;

/**
 * @ClassName StringParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:57
 * VERSION 1.0
 */

public class StringParser extends AtomicParser{

    @Override
    protected void setResult(final String pResult) throws SAXException {
        super.setResult((Object) pResult);
    }
}
