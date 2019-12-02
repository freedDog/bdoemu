package com.bdoemu.commons.xmlrpc.common.parser;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.common.TypeFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcExtensionException;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.apache.ws.commons.util.NamespaceContextImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;

/**
 * @ClassName RecursiveTypeParserImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:23
 * VERSION 1.0
 */

public abstract class RecursiveTypeParserImpl extends TypeParserImpl{

    private final NamespaceContextImpl context;
    protected final XmlRpcStreamConfig cfg;
    private final TypeFactory factory;
    private boolean inValueTag;
    private TypeParser typeParser;
    private StringBuffer text;

    protected RecursiveTypeParserImpl(final XmlRpcStreamConfig pConfig, final NamespaceContextImpl pContext, final TypeFactory pFactory) {
        this.text = new StringBuffer();
        this.cfg = pConfig;
        this.context = pContext;
        this.factory = pFactory;
    }

    protected void startValueTag() throws SAXException {
        this.inValueTag = true;
        this.text.setLength(0);
        this.typeParser = null;
    }

    protected abstract void addResult(final Object p0) throws SAXException;

    protected void endValueTag() throws SAXException {
        if (this.inValueTag) {
            if (this.typeParser == null) {
                this.addResult(this.text.toString());
                this.text.setLength(0);
            }
            else {
                this.typeParser.endDocument();
                try {
                    this.addResult(this.typeParser.getResult());
                }
                catch (XmlRpcException e) {
                    throw new SAXException(e);
                }
                this.typeParser = null;
            }
            return;
        }
        throw new SAXParseException("Invalid state: Not inside value tag.", this.getDocumentLocator());
    }

    @Override
    public void startDocument() throws SAXException {
        this.inValueTag = false;
        this.text.setLength(0);
        this.typeParser = null;
    }

    @Override
    public void endElement(final String pURI, final String pLocalName, final String pQName) throws SAXException {
        if (!this.inValueTag) {
            throw new SAXParseException("Invalid state: Not inside value tag.", this.getDocumentLocator());
        }
        if (this.typeParser == null) {
            throw new SAXParseException("Invalid state: No type parser configured.", this.getDocumentLocator());
        }
        this.typeParser.endElement(pURI, pLocalName, pQName);
    }

    @Override
    public void startElement(final String pURI, final String pLocalName, final String pQName, final Attributes pAttrs) throws SAXException {
        if (this.inValueTag) {
            if (this.typeParser == null) {
                this.typeParser = this.factory.getParser(this.cfg, this.context, pURI, pLocalName);
                if (this.typeParser == null) {
                    if ("http://ws.apache.org/xmlrpc/namespaces/extensions".equals(pURI) && !this.cfg.isEnabledForExtensions()) {
                        final String msg = "The tag " + new QName(pURI, pLocalName) + " is invalid, if isEnabledForExtensions() == false.";
                        throw new SAXParseException(msg, this.getDocumentLocator(), new XmlRpcExtensionException(msg));
                    }
                    throw new SAXParseException("Unknown type: " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                else {
                    this.typeParser.setDocumentLocator(this.getDocumentLocator());
                    this.typeParser.startDocument();
                    if (this.text.length() > 0) {
                        this.typeParser.characters(this.text.toString().toCharArray(), 0, this.text.length());
                        this.text.setLength(0);
                    }
                }
            }
            this.typeParser.startElement(pURI, pLocalName, pQName, pAttrs);
            return;
        }
        throw new SAXParseException("Invalid state: Not inside value tag.", this.getDocumentLocator());
    }

    @Override
    public void characters(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
        if (this.typeParser == null) {
            if (this.inValueTag) {
                this.text.append(pChars, pOffset, pLength);
            }
            else {
                super.characters(pChars, pOffset, pLength);
            }
        }
        else {
            this.typeParser.characters(pChars, pOffset, pLength);
        }
    }

    @Override
    public void ignorableWhitespace(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
        if (this.typeParser == null) {
            if (this.inValueTag) {
                this.text.append(pChars, pOffset, pLength);
            }
            else {
                super.ignorableWhitespace(pChars, pOffset, pLength);
            }
        }
        else {
            this.typeParser.ignorableWhitespace(pChars, pOffset, pLength);
        }
    }

    @Override
    public void processingInstruction(final String pTarget, final String pData) throws SAXException {
        if (this.typeParser == null) {
            super.processingInstruction(pTarget, pData);
        }
        else {
            this.typeParser.processingInstruction(pTarget, pData);
        }
    }

    @Override
    public void skippedEntity(final String pEntity) throws SAXException {
        if (this.typeParser == null) {
            super.skippedEntity(pEntity);
        }
        else {
            this.typeParser.skippedEntity(pEntity);
        }
    }

    @Override
    public void startPrefixMapping(final String pPrefix, final String pURI) throws SAXException {
        if (this.typeParser == null) {
            super.startPrefixMapping(pPrefix, pURI);
        }
        else {
            this.context.startPrefixMapping(pPrefix, pURI);
        }
    }

    @Override
    public void endPrefixMapping(final String pPrefix) throws SAXException {
        if (this.typeParser == null) {
            super.endPrefixMapping(pPrefix);
        }
        else {
            this.context.endPrefixMapping(pPrefix);
        }
    }
}
