package com.bdoemu.commons.xmlrpc.webserver;

import com.bdoemu.commons.xmlrpc.common.util.ThreadPool;
import com.bdoemu.commons.xmlrpc.server.XmlRpcStreamServer;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @ClassName WebServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:56
 * VERSION 1.0
 */

public class WebServer implements Runnable{

    protected ServerSocket serverSocket;
    private Thread listener;
    private ThreadPool pool;
    protected final List<AddressMatcher> accept;
    protected final List<AddressMatcher> deny;
    protected final XmlRpcStreamServer server;
    private InetAddress address;
    private int port;
    private boolean paranoid;
    static final String HTTP_11 = "HTTP/1.1";

    protected XmlRpcStreamServer newXmlRpcStreamServer() {
        return new ConnectionServer();
    }

    public WebServer(final int pPort) {
        this(pPort, null);
    }

    public WebServer(final int pPort, final InetAddress pAddr) {
        this.accept = new ArrayList();
        this.deny = new ArrayList();
        this.server = this.newXmlRpcStreamServer();
        this.address = pAddr;
        this.port = pPort;
    }

    protected ServerSocket createServerSocket(final int pPort, final int backlog, final InetAddress addr) throws IOException {
        return new ServerSocket(pPort, backlog, addr);
    }

    private synchronized void setupServerSocket(final int backlog) throws IOException {
        int i = 1;
        while (true) {
            try {
                this.serverSocket = this.createServerSocket(this.port, backlog, this.address);
                if (this.serverSocket.getSoTimeout() <= 0) {
                    this.serverSocket.setSoTimeout(4096);
                }
            }
            catch (BindException e) {
                if (i == 10) {
                    throw e;
                }
                final long waitUntil = System.currentTimeMillis() + 1000L;
                while (true) {
                    final long l = waitUntil - System.currentTimeMillis();
                    if (l <= 0L) {
                        break;
                    }
                    try {
                        Thread.sleep(l);
                    }
                    catch (InterruptedException ex) {}
                }
                ++i;
                continue;
            }
            break;
        }
    }

    public void start() throws IOException {
        this.setupServerSocket(50);
        if (this.listener == null) {
            (this.listener = new Thread(this, "XML-RPC Weblistener")).start();
        }
    }

    public void setParanoid(final boolean pParanoid) {
        this.paranoid = pParanoid;
    }

    protected boolean isParanoid() {
        return this.paranoid;
    }

    public void acceptClient(final String pAddress) {
        this.accept.add(new AddressMatcher(pAddress));
    }

    public void denyClient(final String pAddress) {
        this.deny.add(new AddressMatcher(pAddress));
    }

    protected boolean allowConnection(final Socket s) {
        if (!this.paranoid) {
            return true;
        }
        int l = this.deny.size();
        final byte[] addr = s.getInetAddress().getAddress();
        for (int i = 0; i < l; ++i) {
            final AddressMatcher match = this.deny.get(i);
            if (match.matches(addr)) {
                return false;
            }
        }
        l = this.accept.size();
        for (int i = 0; i < l; ++i) {
            final AddressMatcher match = this.accept.get(i);
            if (match.matches(addr)) {
                return true;
            }
        }
        return false;
    }

    protected ThreadPool.Task newTask(final WebServer pServer, final XmlRpcStreamServer pXmlRpcServer, final Socket pSocket) throws IOException {
        return new Connection(pServer, pXmlRpcServer, pSocket);
    }

    @Override
    public void run() {
        this.pool = this.newThreadPool();
        try {
            while (this.listener != null) {
                try {
                    Socket socket = this.serverSocket.accept();
                    try {
                        socket.setTcpNoDelay(true);
                    }
                    catch (SocketException socketOptEx) {
                        this.log(socketOptEx);
                    }
                    try {
                        if (this.allowConnection(socket)) {
                            socket.setSoTimeout(30000);
                            final ThreadPool.Task task = this.newTask(this, this.server, socket);
                            if (this.pool.startTask(task)) {
                                socket = null;
                            }
                            else {
                                this.log("Maximum load of " + this.pool.getMaxThreads() + " exceeded, rejecting client");
                            }
                        }
                    }
                    finally {
                        if (socket != null) {
                            try {
                                socket.close();
                            }
                            catch (Throwable t2) {}
                        }
                    }
                }
                catch (InterruptedIOException ex) {}
                catch (Throwable t) {
                    this.log(t);
                }
            }
        }
        finally {
            if (this.serverSocket != null) {
                try {
                    this.serverSocket.close();
                }
                catch (IOException e) {
                    this.log(e);
                }
            }
            this.pool.shutdown();
        }
    }

    protected ThreadPool newThreadPool() {
        return new ThreadPool(this.server.getMaxThreads(), "XML-RPC");
    }

    public synchronized void shutdown() {
        if (this.listener != null) {
            final Thread l = this.listener;
            this.listener = null;
            l.interrupt();
            if (this.pool != null) {
                this.pool.shutdown();
            }
        }
    }

    public int getPort() {
        return this.serverSocket.getLocalPort();
    }

    public void log(final Throwable pError) {
        final String msg = (pError.getMessage() == null) ? pError.getClass().getName() : pError.getMessage();
        this.server.getErrorLogger().log(msg, pError);
    }

    public void log(final String pMessage) {
        this.server.getErrorLogger().log(pMessage);
    }

    public XmlRpcStreamServer getXmlRpcServer() {
        return this.server;
    }

    private class AddressMatcher
    {
        private final int[] pattern;

        AddressMatcher(final String pAddress) {
            try {
                this.pattern = new int[4];
                final StringTokenizer st = new StringTokenizer(pAddress, ".");
                if (st.countTokens() != 4) {
                    throw new IllegalArgumentException();
                }
                for (int i = 0; i < 4; ++i) {
                    final String next = st.nextToken();
                    if ("*".equals(next)) {
                        this.pattern[i] = 256;
                    }
                    else {
                        this.pattern[i] = (byte)Integer.parseInt(next);
                    }
                }
            }
            catch (Exception e) {
                throw new IllegalArgumentException("\"" + pAddress + "\" does not represent a valid IP address");
            }
        }

        boolean matches(final byte[] pAddress) {
            for (int i = 0; i < 4; ++i) {
                if (this.pattern[i] <= 255) {
                    if (this.pattern[i] != pAddress[i]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
