package com.bdoemu.commons.xmlrpc.common.serializer;

import org.apache.ws.commons.util.Base64;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @ClassName SerializableSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:19
 * VERSION 1.0
 */

public class SerializableSerializer extends TypeSerializerImpl{

    public static final String SERIALIZABLE_TAG = "serializable";
    private static final String EX_SERIALIZABLE_TAG = "ex:serializable";

    @Override
    public void write(final ContentHandler pHandler, final Object pObject) throws SAXException {
        pHandler.startElement("", "value", "value", SerializableSerializer.ZERO_ATTRIBUTES);
        pHandler.startElement("", "serializable", "ex:serializable", SerializableSerializer.ZERO_ATTRIBUTES);
        final char[] buffer = new char[1024];
        final Base64.Encoder encoder = (Base64.Encoder)new Base64.SAXEncoder(buffer, 0, (String)null, pHandler);
        try {
            final OutputStream ostream = (OutputStream)new Base64.EncoderOutputStream(encoder);
            final ObjectOutputStream oos = new ObjectOutputStream(ostream);
            oos.writeObject(pObject);
            oos.close();
        }
        catch (Base64.SAXIOException e) {
            throw e.getSAXException();
        }
        catch (IOException e2) {
            throw new SAXException(e2);
        }
        pHandler.endElement("", "serializable", "ex:serializable");
        pHandler.endElement("", "value", "value");
    }
}
