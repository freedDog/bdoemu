package com.bdoemu.commons.xmlrpc.common.parser;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExtParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:37
 * VERSION 1.0
 */

public abstract class ExtParser implements TypeParser{

    private Locator locator;
    private ContentHandler handler;
    private int level;
    private final List prefixes;

    public ExtParser() {
        this.level = 0;
        this.prefixes = new ArrayList();
    }

    protected abstract ContentHandler getExtHandler() throws SAXException;

    protected abstract String getTagName();

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void characters(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
        if (this.handler == null) {
            if (!TypeParserImpl.isEmpty(pChars, pOffset, pLength)) {
                throw new SAXParseException("Unexpected non-whitespace content: " + new String(pChars, pOffset, pLength), this.locator);
            }
        }
        else {
            this.handler.characters(pChars, pOffset, pLength);
        }
    }

    @Override
    public void ignorableWhitespace(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
        if (this.handler != null) {
            this.ignorableWhitespace(pChars, pOffset, pLength);
        }
    }

    @Override
    public void endPrefixMapping(final String pPrefix) throws SAXException {
        if (this.handler != null) {
            this.handler.endPrefixMapping(pPrefix);
        }
    }

    @Override
    public void skippedEntity(final String pName) throws SAXException {
        if (this.handler == null) {
            throw new SAXParseException("Don't know how to handle entity " + pName, this.locator);
        }
        this.handler.skippedEntity(pName);
    }

    @Override
    public void setDocumentLocator(final Locator pLocator) {
        this.locator = pLocator;
        if (this.handler != null) {
            this.handler.setDocumentLocator(pLocator);
        }
    }

    @Override
    public void processingInstruction(final String pTarget, final String pData) throws SAXException {
        if (this.handler != null) {
            this.handler.processingInstruction(pTarget, pData);
        }
    }

    @Override
    public void startPrefixMapping(final String pPrefix, final String pURI) throws SAXException {
        if (this.handler == null) {
            this.prefixes.add(pPrefix);
            this.prefixes.add(pURI);
        }
        else {
            this.handler.startPrefixMapping(pPrefix, pURI);
        }
    }

    @Override
    public void startElement(final String pURI, final String pLocalName, final String pQName, final Attributes pAttrs) throws SAXException {
        switch (this.level++) {
            case 0: {
                final String tag = this.getTagName();
                if (!"http://ws.apache.org/xmlrpc/namespaces/extensions".equals(pURI) || !tag.equals(pLocalName)) {
                    throw new SAXParseException("Expected " + new QName("http://ws.apache.org/xmlrpc/namespaces/extensions", tag) + ", got " + new QName(pURI, pLocalName), this.locator);
                }
                (this.handler = this.getExtHandler()).startDocument();
                for (int i = 0; i < this.prefixes.size(); i += 2) {
                    this.handler.startPrefixMapping((String)this.prefixes.get(i), (String) this.prefixes.get(i + 1));
                }
                break;
            }
            default: {
                this.handler.startElement(pURI, pLocalName, pQName, pAttrs);
                break;
            }
        }
    }

    @Override
    public void endElement(final String pURI, final String pLocalName, final String pQName) throws SAXException {
        switch (--this.level) {
            case 0: {
                for (int i = 0; i < this.prefixes.size(); i += 2) {
                    this.handler.endPrefixMapping((String) this.prefixes.get(i));
                }
                this.handler.endDocument();
                this.handler = null;
                break;
            }
            default: {
                this.handler.endElement(pURI, pLocalName, pQName);
                break;
            }
        }
    }
}
