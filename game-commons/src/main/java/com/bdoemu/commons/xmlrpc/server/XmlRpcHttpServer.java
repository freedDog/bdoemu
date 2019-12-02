package com.bdoemu.commons.xmlrpc.server;

import com.bdoemu.commons.xmlrpc.common.common.ServerStreamConnection;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamRequestConfig;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName XmlRpcHttpServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:08
 * VERSION 1.0
 */

public abstract class XmlRpcHttpServer extends XmlRpcStreamServer{

    protected abstract void setResponseHeader(final ServerStreamConnection p0, final String p1, final String p2);

    @Override
    protected OutputStream getOutputStream(final ServerStreamConnection pConnection, final XmlRpcStreamRequestConfig pConfig, final OutputStream pStream) throws IOException {
        if (pConfig.isEnabledForExtensions() && pConfig.isGzipRequesting()) {
            this.setResponseHeader(pConnection, "Content-Encoding", "gzip");
        }
        return super.getOutputStream(pConnection, pConfig, pStream);
    }
}
