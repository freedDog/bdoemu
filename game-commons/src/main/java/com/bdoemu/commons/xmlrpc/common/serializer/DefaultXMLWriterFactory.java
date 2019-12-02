package com.bdoemu.commons.xmlrpc.common.serializer;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.apache.ws.commons.serialize.CharSetXMLWriter;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.AttributesImpl;

import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @ClassName DefaultXMLWriterFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:21
 * VERSION 1.0
 */

public class DefaultXMLWriterFactory implements XmlWriterFactory{

    private final XmlWriterFactory factory;

    public DefaultXMLWriterFactory() {
        XmlWriterFactory xwf;
        try {
            final CharSetXMLWriter csw = new CharSetXMLWriter();
            final StringWriter sw = new StringWriter();
            csw.setWriter((Writer)sw);
            csw.startDocument();
            csw.startElement("", "test", "test", (Attributes)new AttributesImpl());
            csw.endElement("", "test", "test");
            csw.endDocument();
            xwf = new CharSetXmlWriterFactory();
        }
        catch (Throwable t) {
            xwf = new BaseXmlWriterFactory();
        }
        this.factory = xwf;
    }

    @Override
    public ContentHandler getXmlWriter(final XmlRpcStreamConfig pConfig, final OutputStream pStream) throws XmlRpcException {
        return this.factory.getXmlWriter(pConfig, pStream);
    }
}
