package com.bdoemu.commons.xmlrpc.common.parser;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @ClassName TypeParserImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:24
 * VERSION 1.0
 */

public abstract class TypeParserImpl implements TypeParser{

    private Object result;
    private Locator locator;

    public void setResult(final Object pResult) {
        this.result = pResult;
    }

    @Override
    public Object getResult() throws XmlRpcException {
        return this.result;
    }

    public Locator getDocumentLocator() {
        return this.locator;
    }

    @Override
    public void setDocumentLocator(final Locator pLocator) {
        this.locator = pLocator;
    }

    @Override
    public void processingInstruction(final String pTarget, final String pData) throws SAXException {
    }

    @Override
    public void skippedEntity(final String pName) throws SAXException {
        throw new SAXParseException("Don't know how to handle entity " + pName, this.getDocumentLocator());
    }

    @Override
    public void startPrefixMapping(final String pPrefix, final String pURI) throws SAXException {
    }

    @Override
    public void endPrefixMapping(final String pPrefix) throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startDocument() throws SAXException {
    }

    protected static boolean isEmpty(final char[] pChars, final int pStart, final int pLength) {
        for (int i = 0; i < pLength; ++i) {
            if (!Character.isWhitespace(pChars[pStart + i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void characters(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
        if (!isEmpty(pChars, pOffset, pLength)) {
            throw new SAXParseException("Unexpected non-whitespace character data", this.getDocumentLocator());
        }
    }

    @Override
    public void ignorableWhitespace(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
    }
}
