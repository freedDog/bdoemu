package com.bdoemu.commons.xmlrpc.client;


import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.util.HttpUtil;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName XmlRpcHttpTransport
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:03
 * VERSION 1.0
 */

public abstract class XmlRpcHttpTransport extends XmlRpcStreamTransport{
    public static final String USER_AGENT = "Apache XML RPC";
    private String userAgent;

    protected XmlRpcHttpTransport(final XmlRpcClient pClient, final String pUserAgent) {
        super(pClient);
        this.userAgent = pUserAgent;
    }

    protected String getUserAgent() {
        return this.userAgent;
    }

    protected abstract void setRequestHeader(final String p0, final String p1);

    protected void setCredentials(final XmlRpcHttpClientConfig pConfig) throws XmlRpcClientException {
        String auth;
        try {
            auth = HttpUtil.encodeBasicAuthentication(pConfig.getBasicUserName(), pConfig.getBasicPassword(), pConfig.getBasicEncoding());
        }
        catch (UnsupportedEncodingException e) {
            throw new XmlRpcClientException("Unsupported encoding: " + pConfig.getBasicEncoding(), e);
        }
        if (auth != null) {
            this.setRequestHeader("Authorization", "Basic " + auth);
        }
    }

    protected void setContentLength(final int pLength) {
        this.setRequestHeader("Content-Length", Integer.toString(pLength));
    }

    protected void setCompressionHeaders(final XmlRpcHttpClientConfig pConfig) {
        if (pConfig.isGzipCompressing()) {
            this.setRequestHeader("Content-Encoding", "gzip");
        }
        if (pConfig.isGzipRequesting()) {
            this.setRequestHeader("Accept-Encoding", "gzip");
        }
    }

    protected void initHttpHeaders(final XmlRpcRequest pRequest) throws XmlRpcClientException {
        final XmlRpcHttpClientConfig config = (XmlRpcHttpClientConfig)pRequest.getConfig();
        this.setRequestHeader("Content-Type", "text/xml");
        if (config.getUserAgent() != null) {
            this.setRequestHeader("User-Agent", config.getUserAgent());
        }
        else {
            this.setRequestHeader("User-Agent", this.getUserAgent());
        }
        this.setCredentials(config);
        this.setCompressionHeaders(config);
    }

    @Override
    public Object sendRequest(final XmlRpcRequest pRequest) throws XmlRpcException {
        this.initHttpHeaders(pRequest);
        return super.sendRequest(pRequest);
    }

    protected boolean isUsingByteArrayOutput(final XmlRpcHttpClientConfig pConfig) {
        return !pConfig.isEnabledForExtensions() || !pConfig.isContentLengthOptional();
    }

    @Override
    protected ReqWriter newReqWriter(final XmlRpcRequest pRequest) throws XmlRpcException, IOException, SAXException {
        final XmlRpcHttpClientConfig config = (XmlRpcHttpClientConfig)pRequest.getConfig();
        if (!this.isUsingByteArrayOutput(config)) {
            return super.newReqWriter(pRequest);
        }
        final ByteArrayReqWriter reqWriter = new ByteArrayReqWriter(pRequest);
        this.setContentLength(reqWriter.getContentLength());
        if (this.isCompressingRequest(config)) {
            return new GzipReqWriter(reqWriter);
        }
        return reqWriter;
    }

    protected class ByteArrayReqWriter implements ReqWriter
    {
        private final ByteArrayOutputStream baos;

        ByteArrayReqWriter(final XmlRpcRequest pRequest) throws XmlRpcException, IOException, SAXException {
            this.baos = new ByteArrayOutputStream();
            new ReqWriterImpl(pRequest).write(this.baos);
        }

        protected int getContentLength() {
            return this.baos.size();
        }

        @Override
        public void write(OutputStream pStream) throws IOException {
            try {
                this.baos.writeTo(pStream);
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
}
