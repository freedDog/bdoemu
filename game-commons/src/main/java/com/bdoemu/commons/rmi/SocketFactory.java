package com.bdoemu.commons.rmi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * @ClassName SocketFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/27 14:34
 * VERSION 1.0
 */

public class SocketFactory extends RMISocketFactory {
    @Override
    public Socket createSocket(String host, int port) throws IOException {
        final Socket s = new Socket();
        s.connect(new InetSocketAddress(InetAddress.getByName(host), port));
        return s;
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        final ServerSocket s = new ServerSocket();
        s.bind(new InetSocketAddress(port));
        return s;
    }
}
