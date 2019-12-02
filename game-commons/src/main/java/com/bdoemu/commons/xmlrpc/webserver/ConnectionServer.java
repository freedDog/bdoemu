package com.bdoemu.commons.xmlrpc.webserver;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.common.ServerStreamConnection;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamRequestConfig;
import com.bdoemu.commons.xmlrpc.server.XmlRpcHttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName ConnectionServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:07
 * VERSION 1.0
 */

public class ConnectionServer extends XmlRpcHttpServer {
    @Override
    protected void writeError(final XmlRpcStreamRequestConfig pConfig, final OutputStream pStream, final Throwable pError) throws XmlRpcException {
        final RequestData data = (RequestData)pConfig;
        try {
            if (data.isByteArrayRequired()) {
                super.writeError(pConfig, pStream, pError);
                data.getConnection().writeError(data, pError, (ByteArrayOutputStream)pStream);
            }
            else {
                data.getConnection().writeErrorHeader(data, pError, -1);
                super.writeError(pConfig, pStream, pError);
                pStream.flush();
            }
        }
        catch (IOException e) {
            throw new XmlRpcException(e.getMessage(), e);
        }
    }

    @Override
    protected void writeResponse(final XmlRpcStreamRequestConfig pConfig, final OutputStream pStream, final Object pResult) throws XmlRpcException {
        final RequestData data = (RequestData)pConfig;
        try {
            if (data.isByteArrayRequired()) {
                super.writeResponse(pConfig, pStream, pResult);
                data.getConnection().writeResponse(data, pStream);
            }
            else {
                data.getConnection().writeResponseHeader(data, -1);
                super.writeResponse(pConfig, pStream, pResult);
                pStream.flush();
            }
        }
        catch (IOException e) {
            throw new XmlRpcException(e.getMessage(), e);
        }
    }

    @Override
    protected void setResponseHeader(final ServerStreamConnection pConnection, final String pHeader, final String pValue) {
        ((Connection)pConnection).setResponseHeader(pHeader, pValue);
    }
}
