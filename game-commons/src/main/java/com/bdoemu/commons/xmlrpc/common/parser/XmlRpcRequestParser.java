package com.bdoemu.commons.xmlrpc.common.parser;

import com.bdoemu.commons.xmlrpc.common.common.TypeFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.apache.ws.commons.util.NamespaceContextImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName XmlRpcRequestParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 19:59
 * VERSION 1.0
 */

public class XmlRpcRequestParser extends RecursiveTypeParserImpl{
    private int level;
    private boolean inMethodName;
    private String methodName;
    private List params;

    public XmlRpcRequestParser(final XmlRpcStreamConfig pConfig, final TypeFactory pTypeFactory) {
        super(pConfig, new NamespaceContextImpl(), pTypeFactory);
    }

    @Override
    protected void addResult(final Object pResult) {
        this.params.add(pResult);
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.level = 0;
        this.inMethodName = false;
        this.methodName = null;
        this.params = null;
    }

    @Override
    public void characters(final char[] pChars, final int pOffset, final int pLength) throws SAXException {
        if (this.inMethodName) {
            final String s = new String(pChars, pOffset, pLength);
            this.methodName = ((this.methodName == null) ? s : (this.methodName + s));
        }
        else {
            super.characters(pChars, pOffset, pLength);
        }
    }

    @Override
    public void startElement(final String pURI, final String pLocalName, final String pQName, final Attributes pAttrs) throws SAXException {
        switch (this.level++) {
            case 0: {
                if (!"".equals(pURI) || !"methodCall".equals(pLocalName)) {
                    throw new SAXParseException("Expected root element 'methodCall', got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 1: {
                if (this.methodName == null) {
                    if ("".equals(pURI) && "methodName".equals(pLocalName)) {
                        this.inMethodName = true;
                        break;
                    }
                    throw new SAXParseException("Expected methodName element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                else {
                    if (this.params != null) {
                        throw new SAXParseException("Expected /methodCall, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                    }
                    if ("".equals(pURI) && "params".equals(pLocalName)) {
                        this.params = new ArrayList();
                        break;
                    }
                    throw new SAXParseException("Expected params element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
            }
            case 2: {
                if (!"".equals(pURI) || !"param".equals(pLocalName)) {
                    throw new SAXParseException("Expected param element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 3: {
                if (!"".equals(pURI) || !"value".equals(pLocalName)) {
                    throw new SAXParseException("Expected value element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                this.startValueTag();
                break;
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
                break;
            }
            case 1: {
                if (this.inMethodName) {
                    if ("".equals(pURI) && "methodName".equals(pLocalName)) {
                        if (this.methodName == null) {
                            this.methodName = "";
                        }
                        this.inMethodName = false;
                        break;
                    }
                    throw new SAXParseException("Expected /methodName, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                else {
                    if (!"".equals(pURI) || !"params".equals(pLocalName)) {
                        throw new SAXParseException("Expected /params, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                    }
                    break;
                }
            }
            case 2: {
                if (!"".equals(pURI) || !"param".equals(pLocalName)) {
                    throw new SAXParseException("Expected /param, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 3: {
                if (!"".equals(pURI) || !"value".equals(pLocalName)) {
                    throw new SAXParseException("Expected /value, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                this.endValueTag();
                break;
            }
            default: {
                super.endElement(pURI, pLocalName, pQName);
                break;
            }
        }
    }

    public String getMethodName() {
        return this.methodName;
    }

    public List getParams() {
        return this.params;
    }
}
