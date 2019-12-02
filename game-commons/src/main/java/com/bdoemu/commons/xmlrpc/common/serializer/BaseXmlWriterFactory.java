package com.bdoemu.commons.xmlrpc.common.serializer;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.apache.ws.commons.serialize.XMLWriter;
import org.apache.ws.commons.serialize.XMLWriterImpl;
import org.xml.sax.ContentHandler;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName BaseXmlWriterFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:23
 * VERSION 1.0
 */

public class BaseXmlWriterFactory implements XmlWriterFactory{

    protected XMLWriter newXmlWriter() {
        return new XMLWriterImpl();
    }

    @Override
    public ContentHandler getXmlWriter(final XmlRpcStreamConfig pConfig, final OutputStream pStream) throws XmlRpcException {
        final XMLWriter xw = this.newXmlWriter();
        xw.setDeclarating(true);
        String enc = pConfig.getEncoding();
        if (enc == null) {
            enc = "UTF-8";
        }
        xw.setEncoding(enc);
        xw.setIndenting(false);
        xw.setFlushing(true);
        try {
            xw.setWriter(new BufferedWriter(new OutputStreamWriter(pStream, enc)));
        }
        catch (UnsupportedEncodingException e) {
            throw new XmlRpcException("Unsupported encoding: " + enc, e);
        }
        return (ContentHandler)xw;
    }
}
