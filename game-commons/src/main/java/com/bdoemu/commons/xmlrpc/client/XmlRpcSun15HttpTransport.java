package com.bdoemu.commons.xmlrpc.client;

import com.bdoemu.commons.xmlrpc.common.XmlRpcRequest;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName XmlRpcSun15HttpTransport
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 13:57
 * VERSION 1.0
 */

public class XmlRpcSun15HttpTransport extends XmlRpcSun14HttpTransport {
    private Proxy proxy;

    public XmlRpcSun15HttpTransport(final XmlRpcClient pClient) {
        super(pClient);
    }

    public void setProxy(final Proxy pProxy) {
        this.proxy = pProxy;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    @Override
    protected void initHttpHeaders(final XmlRpcRequest pRequest) throws XmlRpcClientException {
        final XmlRpcHttpClientConfig config = (XmlRpcHttpClientConfig)pRequest.getConfig();
        final int connectionTimeout = config.getConnectionTimeout();
        if (connectionTimeout > 0) {
            this.getURLConnection().setConnectTimeout(connectionTimeout);
        }
        final int replyTimeout = config.getReplyTimeout();
        if (replyTimeout > 0) {
            this.getURLConnection().setReadTimeout(replyTimeout);
        }
        super.initHttpHeaders(pRequest);
    }

    @Override
    protected URLConnection newURLConnection(final URL pURL) throws IOException {
        final Proxy prox = this.getProxy();
        final URLConnection conn = (prox == null) ? pURL.openConnection() : pURL.openConnection(prox);
        final SSLSocketFactory sslSockFactory = this.getSSLSocketFactory();
        if (sslSockFactory != null && conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection)conn).setSSLSocketFactory(sslSockFactory);
        }
        return conn;
    }
}
