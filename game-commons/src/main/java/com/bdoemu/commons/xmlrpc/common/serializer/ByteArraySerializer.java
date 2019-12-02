package com.bdoemu.commons.xmlrpc.common.serializer;

import org.apache.ws.commons.util.Base64;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * @ClassName ByteArraySerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:59
 * VERSION 1.0
 */

public class ByteArraySerializer extends TypeSerializerImpl{

    public static final String BASE_64_TAG = "base64";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        pHandler.startElement("", "value", "value", ByteArraySerializer.ZERO_ATTRIBUTES);
        pHandler.startElement("", "base64", "base64", ByteArraySerializer.ZERO_ATTRIBUTES);
        final byte[] buffer = (byte[])pObject;
        if (buffer.length > 0) {
            final char[] charBuffer = new char[(buffer.length >= 1024) ? 1024 : ((buffer.length + 3) / 4 * 4)];
            final Base64.Encoder encoder = (Base64.Encoder)new Base64.SAXEncoder(charBuffer, 0, (String)null, pHandler);
            try {
                encoder.write(buffer, 0, buffer.length);
                encoder.flush();
            }
            catch (Base64.SAXIOException e) {
                throw e.getSAXException();
            }
            catch (IOException e2) {
                throw new SAXException(e2);
            }
        }
        pHandler.endElement("", "base64", "base64");
        pHandler.endElement("", "value", "value");
    }
}
