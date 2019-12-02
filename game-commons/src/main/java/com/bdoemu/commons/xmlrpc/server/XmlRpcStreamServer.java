package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequestConfig;
import com.bdoemu.commons.xmlrpc.common.common.ServerStreamConnection;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamRequestConfig;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamRequestProcessor;
import com.bdoemu.commons.xmlrpc.common.parser.XmlRpcRequestParser;
import com.bdoemu.commons.xmlrpc.common.serializer.DefaultXMLWriterFactory;
import com.bdoemu.commons.xmlrpc.common.serializer.XmlRpcWriter;
import com.bdoemu.commons.xmlrpc.common.serializer.XmlWriterFactory;
import com.bdoemu.commons.xmlrpc.common.util.SAXParsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @ClassName XmlRpcStreamServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:03
 * VERSION 1.0
 */

public abstract class XmlRpcStreamServer extends XmlRpcServer implements XmlRpcStreamRequestProcessor {
    private static final Logger log;
    private XmlWriterFactory writerFactory;
    private static final XmlRpcErrorLogger theErrorLogger;
    private XmlRpcErrorLogger errorLogger;

    public XmlRpcStreamServer() {
        this.writerFactory = new DefaultXMLWriterFactory();
        this.errorLogger = XmlRpcStreamServer.theErrorLogger;
    }

    protected XmlRpcRequest getRequest(final XmlRpcStreamRequestConfig pConfig, final InputStream pStream) throws XmlRpcException {
        final XmlRpcRequestParser parser = new XmlRpcRequestParser(pConfig, this.getTypeFactory());
        final XMLReader xr = SAXParsers.newXMLReader();
        xr.setContentHandler(parser);
        try {
            xr.parse(new InputSource(pStream));
        }
        catch (SAXException e) {
            final Exception ex = e.getException();
            if (ex != null && ex instanceof XmlRpcException) {
                throw (XmlRpcException)ex;
            }
            throw new XmlRpcException("Failed to parse XML-RPC request: " + e.getMessage(), e);
        }
        catch (IOException e2) {
            throw new XmlRpcException("Failed to read XML-RPC request: " + e2.getMessage(), e2);
        }
        final List params = parser.getParams();
        return new XmlRpcRequest() {
            @Override
            public XmlRpcRequestConfig getConfig() {
                return pConfig;
            }

            @Override
            public String getMethodName() {
                return parser.getMethodName();
            }

            @Override
            public int getParameterCount() {
                return (params == null) ? 0 : params.size();
            }

            @Override
            public Object getParameter(final int pIndex) {
                return params.get(pIndex);
            }
        };
    }

    protected XmlRpcWriter getXmlRpcWriter(final XmlRpcStreamRequestConfig pConfig, final OutputStream pStream) throws XmlRpcException {
        final ContentHandler w = this.getXMLWriterFactory().getXmlWriter(pConfig, pStream);
        return new XmlRpcWriter(pConfig, w, this.getTypeFactory());
    }

    protected void writeResponse(final XmlRpcStreamRequestConfig pConfig, final OutputStream pStream, final Object pResult) throws XmlRpcException {
        try {
            this.getXmlRpcWriter(pConfig, pStream).write(pConfig, pResult);
        }
        catch (SAXException e) {
            throw new XmlRpcException("Failed to write XML-RPC response: " + e.getMessage(), e);
        }
    }

    protected Throwable convertThrowable(final Throwable pError) {
        return pError;
    }

    protected void writeError(final XmlRpcStreamRequestConfig pConfig, final OutputStream pStream, final Throwable pError) throws XmlRpcException {
        final Throwable error = this.convertThrowable(pError);
        int code;
        if (error instanceof XmlRpcException) {
            final XmlRpcException ex = (XmlRpcException)error;
            code = ex.code;
        }
        else {
            code = 0;
        }
        final String message = error.getMessage();
        try {
            this.getXmlRpcWriter(pConfig, pStream).write(pConfig, code, message, error);
        }
        catch (SAXException e) {
            throw new XmlRpcException("Failed to write XML-RPC response: " + e.getMessage(), e);
        }
    }

    public void setXMLWriterFactory(final XmlWriterFactory pFactory) {
        this.writerFactory = pFactory;
    }

    public XmlWriterFactory getXMLWriterFactory() {
        return this.writerFactory;
    }

    protected InputStream getInputStream(final XmlRpcStreamRequestConfig pConfig, final ServerStreamConnection pConnection) throws IOException {
        InputStream istream = pConnection.newInputStream();
        if (pConfig.isEnabledForExtensions() && pConfig.isGzipCompressing()) {
            istream = new GZIPInputStream(istream);
        }
        return istream;
    }

    protected OutputStream getOutputStream(final ServerStreamConnection pConnection, final XmlRpcStreamRequestConfig pConfig, final OutputStream pStream) throws IOException {
        if (pConfig.isEnabledForExtensions() && pConfig.isGzipRequesting()) {
            return new GZIPOutputStream(pStream);
        }
        return pStream;
    }

    protected OutputStream getOutputStream(final XmlRpcStreamRequestConfig pConfig, final ServerStreamConnection pConnection, final int pSize) throws IOException {
        return pConnection.newOutputStream();
    }

    protected boolean isContentLengthRequired(final XmlRpcStreamRequestConfig pConfig) {
        return false;
    }

    @Override
    public void execute(final XmlRpcStreamRequestConfig pConfig, ServerStreamConnection pConnection) throws XmlRpcException {
        XmlRpcStreamServer.log.debug("execute: ->");
        try {
            InputStream istream = null;
            Object result;
            Throwable error;
            try {
                istream = this.getInputStream(pConfig, pConnection);
                final XmlRpcRequest request = this.getRequest(pConfig, istream);
                result = this.execute(request);
                istream.close();
                istream = null;
                error = null;
                XmlRpcStreamServer.log.debug("execute: Request performed successfully");
            }
            catch (Throwable t) {
                this.logError(t);
                result = null;
                error = t;
            }
            finally {
                if (istream != null) {
                    try {
                        istream.close();
                    }
                    catch (Throwable t2) {}
                }
            }
            final boolean contentLengthRequired = this.isContentLengthRequired(pConfig);
            OutputStream ostream;
            ByteArrayOutputStream baos;
            if (contentLengthRequired) {
                baos = (ByteArrayOutputStream)(ostream = new ByteArrayOutputStream());
            }
            else {
                baos = null;
                ostream = pConnection.newOutputStream();
            }
            ostream = this.getOutputStream(pConnection, pConfig, ostream);
            try {
                if (error == null) {
                    this.writeResponse(pConfig, ostream, result);
                }
                else {
                    this.writeError(pConfig, ostream, error);
                }
                ostream.close();
                ostream = null;
            }
            finally {
                if (ostream != null) {
                    try {
                        ostream.close();
                    }
                    catch (Throwable t3) {}
                }
            }
            if (baos != null) {
                OutputStream dest = this.getOutputStream(pConfig, pConnection, baos.size());
                try {
                    baos.writeTo(dest);
                    dest.close();
                    dest = null;
                }
                finally {
                    if (dest != null) {
                        try {
                            dest.close();
                        }
                        catch (Throwable t4) {}
                    }
                }
            }
            pConnection.close();
            pConnection = null;
        }
        catch (IOException e) {
            throw new XmlRpcException("I/O error while processing request: " + e.getMessage(), e);
        }
        finally {
            if (pConnection != null) {
                try {
                    pConnection.close();
                }
                catch (Throwable t5) {}
            }
        }
        XmlRpcStreamServer.log.debug("execute: <-");
    }

    protected void logError(final Throwable t) {
        final String msg = (t.getMessage() == null) ? t.getClass().getName() : t.getMessage();
        this.errorLogger.log(msg, t);
    }

    public XmlRpcErrorLogger getErrorLogger() {
        return this.errorLogger;
    }

    public void setErrorLogger(final XmlRpcErrorLogger pErrorLogger) {
        this.errorLogger = pErrorLogger;
    }

    static {
        log = LoggerFactory.getLogger((Class)XmlRpcStreamServer.class);
        theErrorLogger = new XmlRpcErrorLogger();
    }
}
