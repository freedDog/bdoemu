package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamRequestConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.bdoemu.commons.xmlrpc.common.parser.XmlRpcResponseParser;
import com.bdoemu.commons.xmlrpc.common.serializer.XmlRpcWriter;
import com.bdoemu.commons.xmlrpc.common.util.SAXParsers;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * @ClassName XmlRpcStreamTransport
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:04
 * VERSION 1.0
 */

public abstract class XmlRpcStreamTransport extends XmlRpcTransportImpl {
    protected XmlRpcStreamTransport(final XmlRpcClient pClient) {
        super(pClient);
    }

    protected abstract void close() throws XmlRpcClientException;

    protected abstract boolean isResponseGzipCompressed(final XmlRpcStreamRequestConfig p0);

    protected abstract InputStream getInputStream() throws XmlRpcException;

    protected boolean isCompressingRequest(final XmlRpcStreamRequestConfig pConfig) {
        return pConfig.isEnabledForExtensions() && pConfig.isGzipCompressing();
    }

    protected ReqWriter newReqWriter(final XmlRpcRequest pRequest) throws XmlRpcException, IOException, SAXException {
        ReqWriter reqWriter = new ReqWriterImpl(pRequest);
        if (this.isCompressingRequest((XmlRpcStreamRequestConfig)pRequest.getConfig())) {
            reqWriter = new GzipReqWriter(reqWriter);
        }
        return reqWriter;
    }

    protected abstract void writeRequest(final ReqWriter p0) throws XmlRpcException, IOException, SAXException;

    @Override
    public Object sendRequest(final XmlRpcRequest pRequest) throws XmlRpcException {
        final XmlRpcStreamRequestConfig config = (XmlRpcStreamRequestConfig)pRequest.getConfig();
        boolean closed = false;
        try {
            final ReqWriter reqWriter = this.newReqWriter(pRequest);
            this.writeRequest(reqWriter);
            InputStream istream = this.getInputStream();
            if (this.isResponseGzipCompressed(config)) {
                istream = new GZIPInputStream(istream);
            }
            final Object result = this.readResponse(config, istream);
            closed = true;
            this.close();
            return result;
        }
        catch (IOException e) {
            throw new XmlRpcException("Failed to read server's response: " + e.getMessage(), e);
        }
        catch (SAXException e2) {
            final Exception ex = e2.getException();
            if (ex != null && ex instanceof XmlRpcException) {
                throw (XmlRpcException)ex;
            }
            throw new XmlRpcException("Failed to generate request: " + e2.getMessage(), e2);
        }
        finally {
            if (!closed) {
                try {
                    this.close();
                }
                catch (Throwable t) {}
            }
        }
    }

    protected XMLReader newXMLReader() throws XmlRpcException {
        return SAXParsers.newXMLReader();
    }

    protected Object readResponse(final XmlRpcStreamRequestConfig pConfig, final InputStream pStream) throws XmlRpcException {
        final InputSource isource = new InputSource(pStream);
        final XMLReader xr = this.newXMLReader();
        XmlRpcResponseParser xp;
        try {
            xp = new XmlRpcResponseParser(pConfig, this.getClient().getTypeFactory());
            xr.setContentHandler(xp);
            xr.parse(isource);
        }
        catch (SAXException e) {
            throw new XmlRpcClientException("Failed to parse server's response: " + e.getMessage(), e);
        }
        catch (IOException e2) {
            throw new XmlRpcClientException("Failed to read server's response: " + e2.getMessage(), e2);
        }
        if (xp.isSuccess()) {
            return xp.getResult();
        }
        final Throwable t = xp.getErrorCause();
        if (t == null) {
            throw new XmlRpcException(xp.getErrorCode(), xp.getErrorMessage());
        }
        if (t instanceof XmlRpcException) {
            throw (XmlRpcException)t;
        }
        if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
        }
        throw new XmlRpcException(xp.getErrorCode(), xp.getErrorMessage(), t);
    }

    protected class ReqWriterImpl implements ReqWriter
    {
        private final XmlRpcRequest request;

        protected ReqWriterImpl(final XmlRpcRequest pRequest) {
            this.request = pRequest;
        }

        @Override
        public void write(OutputStream pStream) throws XmlRpcException, IOException, SAXException {
            final XmlRpcStreamConfig config = (XmlRpcStreamConfig)this.request.getConfig();
            try {
                final ContentHandler h = XmlRpcStreamTransport.this.getClient().getXmlWriterFactory().getXmlWriter(config, pStream);
                final XmlRpcWriter xw = new XmlRpcWriter(config, h, XmlRpcStreamTransport.this.getClient().getTypeFactory());
                xw.write(this.request);
                pStream.close();
                pStream = null;
            }
            finally {
                if (pStream != null) {
                    try {
                        pStream.close();
                    }
                    catch (Throwable t) {}
                }
            }
        }
    }

    protected class GzipReqWriter implements ReqWriter
    {
        private final ReqWriter reqWriter;

        protected GzipReqWriter(final ReqWriter pReqWriter) {
            this.reqWriter = pReqWriter;
        }

        @Override
        public void write(OutputStream pStream) throws XmlRpcException, IOException, SAXException {
            try {
                final GZIPOutputStream gStream = new GZIPOutputStream(pStream);
                this.reqWriter.write(gStream);
                pStream.close();
                pStream = null;
            }
            catch (IOException e) {
                throw new XmlRpcException("Failed to write request: " + e.getMessage(), e);
            }
            finally {
                if (pStream != null) {
                    try {
                        pStream.close();
                    }
                    catch (Throwable t) {}
                }
            }
        }
    }

    protected interface ReqWriter
    {
        void write(final OutputStream p0) throws XmlRpcException, IOException, SAXException;
    }
}
