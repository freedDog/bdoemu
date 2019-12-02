package com.bdoemu.commons.xmlrpc.common.parser;

import com.bdoemu.commons.xmlrpc.common.common.TypeFactory;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamRequestConfig;
import org.apache.ws.commons.util.NamespaceContextImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * @ClassName XmlRpcResponseParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:18
 * VERSION 1.0
 */

public class XmlRpcResponseParser extends RecursiveTypeParserImpl{

    private int level;
    private boolean isSuccess;
    private int errorCode;
    private String errorMessage;
    private Throwable errorCause;

    public XmlRpcResponseParser(final XmlRpcStreamRequestConfig pConfig, final TypeFactory pTypeFactory) {
        super(pConfig, new NamespaceContextImpl(), pTypeFactory);
    }

    @Override
    protected void addResult(final Object pResult) throws SAXException {
        if (this.isSuccess) {
            super.setResult(pResult);
        }
        else {
            final Map map = (Map)pResult;
            final Integer faultCode =Integer.valueOf((String) map.get("faultCode"));
            if (faultCode == null) {
                throw new SAXParseException("Missing faultCode", this.getDocumentLocator());
            }
            try {
                this.errorCode = faultCode;
            }
            catch (NumberFormatException e) {
                throw new SAXParseException("Invalid faultCode: " + faultCode, this.getDocumentLocator());
            }
            this.errorMessage =(String) map.get("faultString");
            final Object exception = map.get("faultCause");
            if (exception != null) {
                try {
                    final byte[] bytes = (byte[])exception;
                    final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                    final ObjectInputStream ois = new ObjectInputStream(bais);
                    this.errorCause = (Throwable)ois.readObject();
                    ois.close();
                    bais.close();
                }
                catch (Throwable t) {}
            }
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.level = 0;
        this.isSuccess = false;
        this.errorCode = 0;
        this.errorMessage = null;
    }

    @Override
    public void startElement(final String pURI, final String pLocalName, final String pQName, final Attributes pAttrs) throws SAXException {
        switch (this.level++) {
            case 0: {
                if (!"".equals(pURI) || !"methodResponse".equals(pLocalName)) {
                    throw new SAXParseException("Expected methodResponse element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 1: {
                if ("".equals(pURI) && "params".equals(pLocalName)) {
                    this.isSuccess = true;
                    break;
                }
                if ("".equals(pURI) && "fault".equals(pLocalName)) {
                    this.isSuccess = false;
                    break;
                }
                throw new SAXParseException("Expected params or fault element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
            }
            case 2: {
                if (this.isSuccess) {
                    if (!"".equals(pURI) || !"param".equals(pLocalName)) {
                        throw new SAXParseException("Expected param element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                    }
                    break;
                }
                else {
                    if ("".equals(pURI) && "value".equals(pLocalName)) {
                        this.startValueTag();
                        break;
                    }
                    throw new SAXParseException("Expected value element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
            }
            case 3: {
                if (!this.isSuccess) {
                    super.startElement(pURI, pLocalName, pQName, pAttrs);
                    break;
                }
                if ("".equals(pURI) && "value".equals(pLocalName)) {
                    this.startValueTag();
                    break;
                }
                throw new SAXParseException("Expected value element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
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
                if (!"".equals(pURI) || !"methodResponse".equals(pLocalName)) {
                    throw new SAXParseException("Expected /methodResponse element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 1: {
                String tag;
                if (this.isSuccess) {
                    tag = "params";
                }
                else {
                    tag = "fault";
                }
                if (!"".equals(pURI) || !tag.equals(pLocalName)) {
                    throw new SAXParseException("Expected /" + tag + " element, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
                break;
            }
            case 2: {
                if (this.isSuccess) {
                    if (!"".equals(pURI) || !"param".equals(pLocalName)) {
                        throw new SAXParseException("Expected /param, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                    }
                    break;
                }
                else {
                    if ("".equals(pURI) && "value".equals(pLocalName)) {
                        this.endValueTag();
                        break;
                    }
                    throw new SAXParseException("Expected /value, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
                }
            }
            case 3: {
                if (!this.isSuccess) {
                    super.endElement(pURI, pLocalName, pQName);
                    break;
                }
                if ("".equals(pURI) && "value".equals(pLocalName)) {
                    this.endValueTag();
                    break;
                }
                throw new SAXParseException("Expected /value, got " + new QName(pURI, pLocalName), this.getDocumentLocator());
            }
            default: {
                super.endElement(pURI, pLocalName, pQName);
                break;
            }
        }
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Throwable getErrorCause() {
        return this.errorCause;
    }
}
