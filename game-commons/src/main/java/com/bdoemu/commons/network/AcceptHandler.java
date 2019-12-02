package com.bdoemu.commons.network;

import com.bdoemu.commons.utils.SocketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @ClassName AcceptHandler
 * @Description 接受处理
 * @Author JiangBangMing
 * @Date 2019/6/22 12:24
 * VERSION 1.0
 */

public class AcceptHandler<TClient extends Client<TClient>> implements CompletionHandler<AsynchronousSocketChannel,Void> {

    private static final Logger log = LoggerFactory.getLogger(AcceptHandler.class);

    private final NetworkThread<TClient> server;

   public AcceptHandler(NetworkThread<TClient> server) {
        this.server = server;
    }

    public void submit() {
        if (!this.server.isShutdown()) {
            this.server.getServerSocketChannel().accept(null,this);
        }
    }
    @Override
    public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
        if (this.server.isShutdown()) {
            SocketUtils.closeAsyncChannelSilent(socketChannel);

            return;
        }
        submit();
        try {
            IAcceptFilter acceptFilter = this.server.getAcceptFilter();
            InetSocketAddress socketAddress = (InetSocketAddress)socketChannel.getRemoteAddress();
            if (acceptFilter.isAllowedAddress(socketAddress)) {
                Connection<TClient> connection = new Connection<TClient>(this.server, socketChannel, socketAddress);
                connection.init();
            } else {
                SocketUtils.closeAsyncChannelSilent(socketChannel);
            }
        } catch (Exception ex) {
            log.error("Exception thrown while processing accepted connection", ex);
            SocketUtils.closeAsyncChannelSilent(socketChannel);
        }
    }
    @Override
    public void failed(Throwable reason, Void attachment) {
        if (this.server.isShutdown()) {
            return;
        }
        if (reason instanceof java.nio.channels.ClosedChannelException) {
            return;
        }
        submit();
        log.error("Exception thrown while accepting connection", reason);
    }
}
