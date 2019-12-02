package com.bdoemu.commons.xmlrpc.client;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName XmlRpcSun14HttpTransport
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:03
 * VERSION 1.0
 */

public class XmlRpcSun14HttpTransport extends XmlRpcSunHttpTransport{

    private SSLSocketFactory sslSocketFactory;

    public XmlRpcSun14HttpTransport(final XmlRpcClient pClient) {
        super(pClient);
    }

    public void setSSLSocketFactory(final SSLSocketFactory pSocketFactory) {
        this.sslSocketFactory = pSocketFactory;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.sslSocketFactory;
    }

    @Override
    protected URLConnection newURLConnection(final URL pURL) throws IOException {
        final URLConnection conn = super.newURLConnection(pURL);
        final SSLSocketFactory sslSockFactory = this.getSSLSocketFactory();
        if (sslSockFactory != null && conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection)conn).setSSLSocketFactory(sslSockFactory);
        }
        return conn;
    }
}
