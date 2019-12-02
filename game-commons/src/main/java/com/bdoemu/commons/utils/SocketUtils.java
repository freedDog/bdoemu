package com.bdoemu.commons.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @ClassName SocketUtils
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/22 12:26
 * VERSION 1.0
 */

public class SocketUtils {
    private static final Logger log;
    private static final SocketOption[] STANDARD_SOCKET_OPTIONS;

    static  {
        log= LoggerFactory.getLogger(SocketUtils.class);
        STANDARD_SOCKET_OPTIONS=new SocketOption[5];
    }

    public static Pair<SocketOption, Object>[] parseSocketOptions(String sockOptStr) throws IllegalArgumentException {
        return null;
    }

    public static <TNetworkChannel extends java.nio.channels.NetworkChannel> TNetworkChannel applySocketOptions(TNetworkChannel networkChannel, Pair[] options) throws IOException {
        return null;
    }

    public static final void closeAsyncChannelSilent(AsynchronousSocketChannel socketChannel) {
        try {
            socketChannel.shutdownInput();
        } catch (IOException iOException) {}

        try {
            socketChannel.shutdownOutput();
        } catch (IOException iOException) {}

        try {
            socketChannel.close();
        } catch (IOException iOException) {}
    }
}
