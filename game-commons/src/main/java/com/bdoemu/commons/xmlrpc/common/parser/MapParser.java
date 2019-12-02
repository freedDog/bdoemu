package com.bdoemu.commons.xmlrpc.common.parser;

import com.bdoemu.commons.xmlrpc.common.common.TypeFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.apache.ws.commons.util.NamespaceContextImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:55
 * VERSION 1.0
 */

public class MapParser extends RecursiveTypeParserImpl{
    private int level;
    private StringBuffer nameBuffer;
    private Object nameObject;
    private Map map;
    private boolean inName;
    private boolean inValue;
    private boolean doneValue;

    public MapParser(final XmlRpcStreamConfig pConfig, final NamespaceContextImpl pContext, final TypeFactory pFactory) {
        super(pConfig, pContext, pFactory);
        this.level = 0;
        this.nameBuffer = new StringBuffer();
    }

    @Override
    protected void addResult(final Object pResult) throws SAXException {
        if (this.inName) {
            this.nameObject = pResult;
        }
        else {
            if (this.nameObject == null) {
                throw new SAXParseException("Invalid state: Expected name", this.getDocumentLocator());
            }
            if (this.map.containsKey(this.nameObject)) {
                throw new SAXParseException("Duplicate name: " + this.nameObject, this.getDocumentLocator());
            }
            this.map.put(this.nameObject, pResult);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.level = 0;
        this.map = new HashMap();
        final boolean b = false;
        this.inName = b;
        this.inValue = b;
    }

    @Override
    public void characters(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
        if (this.inName && !this.inValue) {
            this.nameBuffer.append(pChars, pOffset, pLength);
        }
        else {
            super.characters(pChars, pOffset, pLength);
        }
    }

    @Override
    public void ignorableWhitespace(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
        if (this.inName) {
            this.characters(pChars, pOffset, pLength);
        }
        else {
            super.ignorableWhitespace(pChars, pOffset, pLength);
        }
    }

    @Override
    public void startElement(final String pURI, final String pLocalName, final String pQName, final Attributes pAttrs) throws SAXException {
        switch (this.level++) {
            case 0: {
                if (!"".equals(pURI) || !"struct".equals(pLocalName)) {
                    throw new SAXParseException("Expected struct, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 1: {
                if (!"".equals(pURI) || !"member".equals(pLocalName)) {
                    throw new SAXParseException("Expected member, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                final boolean doneValue = false;
                this.inValue = doneValue;
                this.inName = doneValue;
                this.doneValue = doneValue;
                this.nameObject = null;
                this.nameBuffer.setLength(0);
                break;
            }
            case 2: {
                if (this.doneValue) {
                    throw new SAXParseException("Expected /member, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                if ("".equals(pURI) && "name".equals(pLocalName)) {
                    if (this.nameObject == null) {
                        this.inName = true;
                        break;
                    }
                    throw new SAXParseException("Expected value, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                else {
                    if (!"".equals(pURI) || !"value".equals(pLocalName)) {
                        break;
                    }
                    if (this.nameObject == null) {
                        throw new SAXParseException("Expected name, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                    }
                    this.inValue = true;
                    this.startValueTag();
                    break;
                }
            }
            case 3: {
                if (!this.inName || !"".equals(pURI) || !"value".equals(pLocalName)) {
                    super.startElement(pURI, pLocalName, pQName, pAttrs);
                    break;
                }
                if (this.cfg.isEnabledForExtensions()) {
                    this.inValue = true;
                    this.startValueTag();
                    break;
                }
                throw new SAXParseException("Expected /name, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
            }
            default: {
                super.startElement(pURI, pLocalName, pQName, pAttrs);
                break;
            }
        }
    }

    @Override
    public void endElement(final String pURI, final String pLocalName, final String pQName) throws SAXException {
        switch (--this.level) {
            case 0: {
                this.setResult(this.map);
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                if (this.inName) {
                    this.inName = false;
                    if (this.nameObject == null) {
                        this.nameObject = this.nameBuffer.toString();
                        break;
                    }
                    for (int i = 0; i < this.nameBuffer.length(); ++i) {
                        if (!Character.isWhitespace(this.nameBuffer.charAt(i))) {
                            throw new SAXParseException("Unexpected non-whitespace character in member name", this.getDocumentLocator());
                        }
                    }
                    break;
                }
                else {
                    if (this.inValue) {
                        this.endValueTag();
                        this.doneValue = true;
                        break;
                    }
                    break;
                }
            }
            case 3: {
                if (this.inName && this.inValue && "".equals(pURI) && "value".equals(pLocalName)) {
                    this.endValueTag();
                    break;
                }
                super.endElement(pURI, pLocalName, pQName);
                break;
            }
            default: {
                super.endElement(pURI, pLocalName, pQName);
                break;
            }
        }
    }
}
