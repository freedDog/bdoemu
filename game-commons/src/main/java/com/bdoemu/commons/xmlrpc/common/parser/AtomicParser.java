package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;

/**
 * @ClassName AtomicParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:17
 * VERSION 1.0
 */

public abstract class AtomicParser extends TypeParserImpl{

    private int level;
    protected StringBuffer sb;

    protected AtomicParser() {
    }

    protected abstract void setResult(final String p0) throws SAXException;

    @Override
    public void startDocument() throws SAXException {
        this.level = 0;
    }

    @Override
    public void characters(final char[] pChars, final int pStart, final int pLength) throws SAXException {
        if (this.sb == null) {
            if (!TypeParserImpl.isEmpty(pChars, pStart, pLength)) {
                throw new SAXParseException("Unexpected non-whitespace characters", this.getDocumentLocator());
            }
        }
        else {
            this.sb.append(pChars, pStart, pLength);
        }
    }

    @Override
    public void endElement(final String pURI, final String pLocalName, final String pQName) throws SAXException {
        final int level = this.level - 1;
        this.level = level;
        if (level == 0) {
            this.setResult(this.sb.toString());
            return;
        }
        throw new SAXParseException("Unexpected end tag in atomic element: " + new QName(pURI, pLocalName), this.getDocumentLocator());
    }

    @Override
    public void startElement(final String pURI, final String pLocalName, final String pQName, final Attributes pAttrs) throws SAXException {
        if (this.level++ == 0) {
            this.sb = new StringBuffer();
            return;
        }
        throw new SAXParseException("Unexpected start tag in atomic element: " + new QName(pURI, pLocalName), this.getDocumentLocator());
    }
}
