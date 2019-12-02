package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamRequestConfig;
import com.bdoemu.commons.xmlrpc.common.util.HttpUtil;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName XmlRpcSunHttpTransport
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:03
 * VERSION 1.0
 */

public class XmlRpcSunHttpTransport extends XmlRpcHttpTransport{


    private static final String userAgent = "Apache XML RPC (Sun HTTP Transport)";
    private URLConnection conn;

    public XmlRpcSunHttpTransport(final XmlRpcClient pClient) {
        super(pClient, "Apache XML RPC (Sun HTTP Transport)");
    }

    protected URLConnection newURLConnection(final URL pURL) throws IOException {
        return pURL.openConnection();
    }

    protected URLConnection getURLConnection() {
        return this.conn;
    }

    @Override
    public Object sendRequest(final XmlRpcRequest pRequest) throws XmlRpcException {
        final XmlRpcHttpClientConfig config = (XmlRpcHttpClientConfig)pRequest.getConfig();
        try {
            final URLConnection urlConnection = this.newURLConnection(config.getServerURL());
            this.conn = urlConnection;
            final URLConnection c = urlConnection;
            c.setUseCaches(false);
            c.setDoInput(true);
            c.setDoOutput(true);
        }
        catch (IOException e) {
            throw new XmlRpcException("Failed to create URLConnection: " + e.getMessage(), e);
        }
        return super.sendRequest(pRequest);
    }

    @Override
    protected void setRequestHeader(final String pHeader, final String pValue) {
        this.getURLConnection().setRequestProperty(pHeader, pValue);
    }

    @Override
    protected void close() throws XmlRpcClientException {
        final URLConnection c = this.getURLConnection();
        if (c instanceof HttpURLConnection) {
            ((HttpURLConnection)c).disconnect();
        }
    }

    @Override
    protected boolean isResponseGzipCompressed(final XmlRpcStreamRequestConfig pConfig) {
        return HttpUtil.isUsingGzipEncoding(this.getURLConnection().getHeaderField("Content-Encoding"));
    }

    @Override
    protected InputStream getInputStream() throws XmlRpcException {
        try {
            final URLConnection connection = this.getURLConnection();
            if (connection instanceof HttpURLConnection) {
                final HttpURLConnection httpConnection = (HttpURLConnection)connection;
                final int responseCode = httpConnection.getResponseCode();
                if (responseCode < 200 || responseCode > 299) {
                    throw new XmlRpcHttpTransportException(responseCode, httpConnection.getResponseMessage());
                }
            }
            return connection.getInputStream();
        }
        catch (IOException e) {
            throw new XmlRpcException("Failed to create input stream: " + e.getMessage(), e);
        }
    }

    @Override
    protected void writeRequest(final ReqWriter pWriter) throws IOException, XmlRpcException, SAXException {
        pWriter.write(this.getURLConnection().getOutputStream());
    }
}
