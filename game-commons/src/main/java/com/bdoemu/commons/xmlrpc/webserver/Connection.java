package com.bdoemu.commons.xmlrpc.webserver;


import com.bdoemu.commons.xmlrpc.common.common.ServerStreamConnection;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcNotAuthorizedException;
import com.bdoemu.commons.xmlrpc.common.util.HttpUtil;
import com.bdoemu.commons.xmlrpc.common.util.LimitedInputStream;
import com.bdoemu.commons.xmlrpc.common.util.ThreadPool;
import com.bdoemu.commons.xmlrpc.server.XmlRpcHttpServerConfig;
import com.bdoemu.commons.xmlrpc.server.XmlRpcStreamServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @ClassName Connection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:11
 * VERSION 1.0
 */

public class Connection implements ThreadPool.InterruptableTask, ServerStreamConnection {

    private static final String US_ASCII = "US-ASCII";
    private static final byte[] ctype;
    private static final byte[] clength;
    private static final byte[] newline;
    private static final byte[] doubleNewline;
    private static final byte[] conkeep;
    private static final byte[] conclose;
    private static final byte[] ok;
    private static final byte[] serverName;
    private static final byte[] wwwAuthenticate;
    private final WebServer webServer;
    private final Socket socket;
    private final InputStream input;
    private final OutputStream output;
    private final XmlRpcStreamServer server;
    private byte[] buffer;
    private Map<String,String> headers;
    private RequestData requestData;
    private boolean shuttingDown;
    private boolean firstByte;

    private static final byte[] toHTTPBytes(final String text) {
        try {
            return text.getBytes("US-ASCII");
        }
        catch (UnsupportedEncodingException e) {
            throw new Error(e.getMessage() + ": HTTP requires US-ASCII encoding");
        }
    }

    public Connection(final WebServer pWebServer, final XmlRpcStreamServer pServer, final Socket pSocket) throws IOException {
        this.webServer = pWebServer;
        this.server = pServer;
        this.socket = pSocket;
        this.input = new BufferedInputStream(this.socket.getInputStream()) {
            @Override
            public void close() throws IOException {
            }
        };
        this.output = new BufferedOutputStream(this.socket.getOutputStream());
    }

    private RequestData getRequestConfig() throws IOException {
        this.requestData = new RequestData(this);
        if (this.headers != null) {
            this.headers.clear();
        }
        this.firstByte = true;
        final XmlRpcHttpServerConfig serverConfig = (XmlRpcHttpServerConfig)this.server.getConfig();
        this.requestData.setBasicEncoding(serverConfig.getBasicEncoding());
        this.requestData.setContentLengthOptional(serverConfig.isContentLengthOptional());
        this.requestData.setEnabledForExtensions(serverConfig.isEnabledForExtensions());
        this.requestData.setEnabledForExceptions(serverConfig.isEnabledForExceptions());
        String line = this.readLine();
        if (line == null && this.firstByte) {
            return null;
        }
        if (line != null && line.length() == 0) {
            line = this.readLine();
            if (line == null || line.length() == 0) {
                return null;
            }
        }
        final StringTokenizer tokens = new StringTokenizer(line);
        final String method = tokens.nextToken();
        if (!"POST".equalsIgnoreCase(method)) {
            throw new BadRequestException(this.requestData, method);
        }
        this.requestData.setMethod(method);
        tokens.nextToken();
        final String httpVersion = tokens.nextToken();
        this.requestData.setHttpVersion(httpVersion);
        this.requestData.setKeepAlive(serverConfig.isKeepAliveEnabled() && "HTTP/1.1".equals(httpVersion));
        do {
            line = this.readLine();
            if (line != null) {
                final String lineLower = line.toLowerCase();
                if (lineLower.startsWith("content-length:")) {
                    final String cLength = line.substring("content-length:".length());
                    this.requestData.setContentLength(Integer.parseInt(cLength.trim()));
                }
                else if (lineLower.startsWith("connection:")) {
                    this.requestData.setKeepAlive(serverConfig.isKeepAliveEnabled() && lineLower.indexOf("keep-alive") > -1);
                }
                else if (lineLower.startsWith("authorization:")) {
                    final String credentials = line.substring("authorization:".length());
                    HttpUtil.parseAuthorization(this.requestData, credentials);
                }
                else {
                    if (!lineLower.startsWith("transfer-encoding:")) {
                        continue;
                    }
                    final String transferEncoding = line.substring("transfer-encoding:".length());
                    final String nonIdentityEncoding = HttpUtil.getNonIdentityTransferEncoding(transferEncoding);
                    if (nonIdentityEncoding != null) {
                        throw new BadEncodingException(this.requestData, nonIdentityEncoding);
                    }
                    continue;
                }
            }
        } while (line != null && line.length() != 0);
        return this.requestData;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                final RequestData data = this.getRequestConfig();
                if (data == null) {
                    break;
                }
                this.server.execute(data, this);
                this.output.flush();
                if (!data.isKeepAlive()) {
                    break;
                }
                if (!data.isSuccess()) {
                    break;
                }
                ++i;
            }
        }
        catch (RequestException e) {
            this.webServer.log(e.getClass().getName() + ": " + e.getMessage());
            try {
                this.writeErrorHeader(e.requestData, e, -1);
                this.output.flush();
            }
            catch (IOException ex) {}
        }
        catch (Throwable t) {
            if (!this.shuttingDown) {
                this.webServer.log(t);
            }
        }
        finally {
            try {
                this.output.close();
            }
            catch (Throwable t2) {}
            try {
                this.input.close();
            }
            catch (Throwable t3) {}
            try {
                this.socket.close();
            }
            catch (Throwable t4) {}
        }
    }

    private String readLine() throws IOException {
        if (this.buffer == null) {
            this.buffer = new byte[2048];
        }
        int count = 0;
        while (true) {
            int next;
            try {
                next = this.input.read();
                this.firstByte = false;
            }
            catch (SocketException e) {
                if (this.firstByte) {
                    return null;
                }
                throw e;
            }
            if (next < 0 || next == 10) {
                return new String(this.buffer, 0, count, "US-ASCII");
            }
            if (next != 13) {
                this.buffer[count++] = (byte)next;
            }
            if (count >= this.buffer.length) {
                throw new IOException("HTTP Header too long");
            }
        }
    }

    public void writeResponse(final RequestData pData, final OutputStream pBuffer) throws IOException {
        final ByteArrayOutputStream response = (ByteArrayOutputStream)pBuffer;
        this.writeResponseHeader(pData, response.size());
        response.writeTo(this.output);
    }

    public void writeResponseHeader(final RequestData pData, final int pContentLength) throws IOException {
        this.output.write(toHTTPBytes(pData.getHttpVersion()));
        this.output.write(Connection.ok);
        this.output.write(Connection.serverName);
        this.output.write(pData.isKeepAlive() ? Connection.conkeep : Connection.conclose);
        this.output.write(Connection.ctype);
        if (this.headers != null) {
            for (final Map.Entry<String,String> entry : this.headers.entrySet()) {
                final String header = entry.getKey();
                final String value = entry.getValue();
                this.output.write(toHTTPBytes(header + ": " + value + "\r\n"));
            }
        }
        if (pContentLength != -1) {
            this.output.write(Connection.clength);
            this.output.write(toHTTPBytes(Integer.toString(pContentLength)));
            this.output.write(Connection.doubleNewline);
        }
        else {
            this.output.write(Connection.newline);
        }
        pData.setSuccess(true);
    }

    public void writeError(final RequestData pData, final Throwable pError, final ByteArrayOutputStream pStream) throws IOException {
        this.writeErrorHeader(pData, pError, pStream.size());
        pStream.writeTo(this.output);
        this.output.flush();
    }

    public void writeErrorHeader(final RequestData pData, final Throwable pError, final int pContentLength) throws IOException {
        if (pError instanceof BadRequestException) {
            final byte[] content = toHTTPBytes("Method " + pData.getMethod() + " not implemented (try POST)\r\n");
            this.output.write(toHTTPBytes(pData.getHttpVersion()));
            this.output.write(toHTTPBytes(" 400 Bad Request"));
            this.output.write(Connection.newline);
            this.output.write(Connection.serverName);
            this.writeContentLengthHeader(content.length);
            this.output.write(Connection.newline);
            this.output.write(content);
        }
        else if (pError instanceof BadEncodingException) {
            final byte[] content = toHTTPBytes("The Transfer-Encoding " + pError.getMessage() + " is not implemented.\r\n");
            this.output.write(toHTTPBytes(pData.getHttpVersion()));
            this.output.write(toHTTPBytes(" 501 Not Implemented"));
            this.output.write(Connection.newline);
            this.output.write(Connection.serverName);
            this.writeContentLengthHeader(content.length);
            this.output.write(Connection.newline);
            this.output.write(content);
        }
        else if (pError instanceof XmlRpcNotAuthorizedException) {
            final byte[] content = toHTTPBytes("Method " + pData.getMethod() + " requires a valid user name and password.\r\n");
            this.output.write(toHTTPBytes(pData.getHttpVersion()));
            this.output.write(toHTTPBytes(" 401 Unauthorized"));
            this.output.write(Connection.newline);
            this.output.write(Connection.serverName);
            this.writeContentLengthHeader(content.length);
            this.output.write(Connection.wwwAuthenticate);
            this.output.write(Connection.newline);
            this.output.write(content);
        }
        else {
            this.output.write(toHTTPBytes(pData.getHttpVersion()));
            this.output.write(Connection.ok);
            this.output.write(Connection.serverName);
            this.output.write(Connection.conclose);
            this.output.write(Connection.ctype);
            this.writeContentLengthHeader(pContentLength);
            this.output.write(Connection.newline);
        }
    }

    private void writeContentLengthHeader(final int pContentLength) throws IOException {
        if (pContentLength == -1) {
            return;
        }
        this.output.write(Connection.clength);
        this.output.write(toHTTPBytes(Integer.toString(pContentLength)));
        this.output.write(Connection.newline);
    }

    public void setResponseHeader(final String pHeader, final String pValue) {
        this.headers.put(pHeader, pValue);
    }

    @Override
    public OutputStream newOutputStream() throws IOException {
        final boolean useContentLength = !this.requestData.isEnabledForExtensions() || !this.requestData.isContentLengthOptional();
        if (useContentLength) {
            return new ByteArrayOutputStream();
        }
        return this.output;
    }

    @Override
    public InputStream newInputStream() throws IOException {
        final int contentLength = this.requestData.getContentLength();
        if (contentLength == -1) {
            return this.input;
        }
        return new LimitedInputStream(this.input, contentLength);
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void shutdown() throws Throwable {
        this.shuttingDown = true;
        this.socket.close();
    }

    static {
        ctype = toHTTPBytes("Content-Type: text/xml\r\n");
        clength = toHTTPBytes("Content-Length: ");
        newline = toHTTPBytes("\r\n");
        doubleNewline = toHTTPBytes("\r\n\r\n");
        conkeep = toHTTPBytes("Connection: Keep-Alive\r\n");
        conclose = toHTTPBytes("Connection: close\r\n");
        ok = toHTTPBytes(" 200 OK\r\n");
        serverName = toHTTPBytes("Server: Apache XML-RPC 1.0\r\n");
        wwwAuthenticate = toHTTPBytes("WWW-Authenticate: Basic realm=XML-RPC\r\n");
    }

    private abstract static class RequestException extends IOException
    {
        private static final long serialVersionUID = 2113732921468653309L;
        private final RequestData requestData;

        RequestException(final RequestData pData, final String pMessage) {
            super(pMessage);
            this.requestData = pData;
        }

        RequestData getRequestData() {
            return this.requestData;
        }
    }

    private static class BadEncodingException extends RequestException
    {
        private static final long serialVersionUID = -2674424938251521248L;

        BadEncodingException(final RequestData pData, final String pTransferEncoding) {
            super(pData, pTransferEncoding);
        }
    }

    private static class BadRequestException extends RequestException
    {
        private static final long serialVersionUID = 3257848779234554934L;

        BadRequestException(final RequestData pData, final String pTransferEncoding) {
            super(pData, pTransferEncoding);
        }
    }
}
