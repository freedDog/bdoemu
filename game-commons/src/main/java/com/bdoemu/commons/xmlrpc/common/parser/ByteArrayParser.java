package com.bdoemu.commons.xmlrpc.common.parser;

import org.apache.ws.commons.util.Base64;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName ByteArrayParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:46
 * VERSION 1.0
 */

public class ByteArrayParser extends TypeParserImpl{

    private int level;
    private ByteArrayOutputStream baos;
    private Base64.Decoder decoder;

    @Override
    public void startDocument() throws SAXException {
        this.level = 0;
    }

    @Override
    public void characters(final char[] pChars, final int pStart, final int pLength) throws SAXException {
        if (this.baos == null) {
            if (!TypeParserImpl.isEmpty(pChars, pStart, pLength)) {
                throw new SAXParseException("Unexpected non-whitespace characters", this.getDocumentLocator());
            }
        }
        else {
            try {
                this.decoder.write(pChars, pStart, pLength);
            }
            catch (IOException e) {
                throw new SAXParseException("Failed to decode base64 stream.", this.getDocumentLocator(), e);
            }
        }
    }

    @Override
    public void endElement(final String pURI, final String pLocalName, final String pQName) throws SAXException {
        final int level = this.level - 1;
        this.level = level;
        if (level == 0) {
            try {
                this.decoder.flush();
            }
            catch (IOException e) {
                throw new SAXParseException("Failed to decode base64 stream.", this.getDocumentLocator(), e);
            }
            this.setResult(this.baos.toByteArray());
            return;
        }
        throw new SAXParseException("Unexpected end tag in atomic element: " + new QName(pURI, pLocalName), this.getDocumentLocator());
    }

    @Override
    public void startElement(final String pURI, final String pLocalName, final String pQName, final Attributes pAttrs) throws SAXException {
        if (this.level++ == 0) {
            this.baos = new ByteArrayOutputStream();
            this.decoder = new Base64.Decoder(1024) {
                @Override
                protected void writeBuffer(final byte[] pBytes, final int pOffset, final int pLen) throws IOException {
                    ByteArrayParser.this.baos.write(pBytes, pOffset, pLen);
                }
            };
            return;
        }
        throw new SAXParseException("Unexpected start tag in atomic element: " + new QName(pURI, pLocalName), this.getDocumentLocator());
    }
}
