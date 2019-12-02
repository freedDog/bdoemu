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
 * @ClassName ObjectArrayParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:53
 * VERSION 1.0
 */

public class ObjectArrayParser extends RecursiveTypeParserImpl{

    private int level;
    private List list;

    public ObjectArrayParser(final XmlRpcStreamConfig pConfig, final NamespaceContextImpl pContext, final TypeFactory pFactory) {
        super(pConfig, pContext, pFactory);
        this.level = 0;
    }

    @Override
    public void startDocument() throws SAXException {
        this.level = 0;
        this.list = new ArrayList();
        super.startDocument();
    }

    @Override
    protected void addResult(final Object pValue) {
        this.list.add(pValue);
    }

    @Override
    public void endElement(final String pURI, final String pLocalName, final String pQName) throws SAXException {
        switch (--this.level) {
            case 0: {
                this.setResult(this.list.toArray());
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                this.endValueTag();
                break;
            }
            default: {
                super.endElement(pURI, pLocalName, pQName);
                break;
            }
        }
    }

    @Override
    public void startElement(final String pURI, final String pLocalName, final String pQName, final Attributes pAttrs) throws SAXException {
        switch (this.level++) {
            case 0: {
                if (!"".equals(pURI) || !"array".equals(pLocalName)) {
                    throw new SAXParseException("Expected array element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 1: {
                if (!"".equals(pURI) || !"data".equals(pLocalName)) {
                    throw new SAXParseException("Expected data element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 2: {
                if (!"".equals(pURI) || !"value".equals(pLocalName)) {
                    throw new SAXParseException("Expected data element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
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
}
