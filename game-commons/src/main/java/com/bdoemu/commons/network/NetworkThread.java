package com.bdoemu.commons.network;

import com.bdoemu.commons.network.handler.AbstractPacketHandlerFactory;
import com.bdoemu.commons.thread.ThreadPool;
import com.bdoemu.commons.utils.SocketUtils;
import com.bdoemu.core.configs.NetworkConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.nio.ByteOrder;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @ClassName NetworkThread
 * @Description 网络线程
 * @Author JiangBangMing
 * @Date 2019/6/22 12:21
 * VERSION 1.0
 */

public abstract class NetworkThread<TClient extends Client<TClient>> {

    private static final Logger log = LoggerFactory.getLogger(NetworkThread.class);

    private final ServerBootstrap _bootstrap;

    private final EventLoopGroup _mainLoopGroup;

    private final EventLoopGroup _workerLoopGroup;

    private ChannelFuture _future;
    private boolean isShutdown;

    private InetSocketAddress bindAddress;
    private Pair<SocketOption, Object>[] clientSocketOptions;
    private Pair<SocketOption, Object>[] serverSocketOptions;
    private final AcceptHandler<TClient> acceptHandler;
    private final AlwaysAcceptFilter ACCEPT_FILTER;
    private AsynchronousChannelGroup channelGroup;
    protected AsynchronousServerSocketChannel serverSocketChannel;

    public NetworkThread() {
        this._bootstrap = new ServerBootstrap();
        this._mainLoopGroup = SystemUtils.IS_OS_WINDOWS ? new NioEventLoopGroup(1) : new EpollEventLoopGroup(1);
        this._workerLoopGroup = SystemUtils.IS_OS_WINDOWS ? new NioEventLoopGroup(16) : new EpollEventLoopGroup(16);
        this._bootstrap.group(this._mainLoopGroup, this._workerLoopGroup)
                .channel(SystemUtils.IS_OS_WINDOWS ? io.netty.channel.socket.nio.NioServerSocketChannel.class : io.netty.channel.epoll.EpollServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    public void initChannel(SocketChannel param1SocketChannel) throws Exception
                    {
                        param1SocketChannel.config().setPerformancePreferences(0, 2, 1);

                        ChannelPipeline channelPipeline = param1SocketChannel.pipeline();

                        channelPipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(240));
                        channelPipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, NetworkConfig.MAX_INCOME_PACKET_SIZE, 0, 2, -2, 0, true));

                        channelPipeline.addLast("bytesEncoder", new ByteArrayEncoder());
                        channelPipeline.addLast(new ChannelHandler[] { new Connection(NetworkThread.this.getThis())

                        });
                    }
                }).option(ChannelOption.SO_RCVBUF,NetworkConfig.RECV_BUFFER_SIZE)
                .childOption(ChannelOption.SO_SNDBUF, NetworkConfig.SEND_BUFFER_SIZE)
                .option(ChannelOption.SO_BACKLOG, NetworkConfig.SERVER_SOCKET_BACKLOG)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true))
                .childOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));

        this.ACCEPT_FILTER = new AlwaysAcceptFilter();
        this.isShutdown = false;
        this.acceptHandler = new AcceptHandler(this);
    }

    public final NetworkThread<TClient> getThis() {
        return this;
    }

    public abstract IClientFactory<TClient> getClientFactory();

    public abstract AbstractPacketHandlerFactory<TClient> getPacketHandler();

    Pair<SocketOption, Object>[] getClientSocketOptions() {
        if (this.clientSocketOptions == null) {
            this.clientSocketOptions = SocketUtils.parseSocketOptions(NetworkConfig.CLIENT_SOCKET_OPTIONS);
        }
        return this.clientSocketOptions;
    }

    private Pair<SocketOption, Object>[] getServerSocketOptions() {
        if (this.serverSocketOptions == null) {
            this.serverSocketOptions = SocketUtils.parseSocketOptions(NetworkConfig.SERVER_SOCKET_OPTIONS);
        }
        return this.serverSocketOptions;
    }

    private AsynchronousChannelGroup getChannelGroup() {
        if (this.channelGroup == null) {
            try {
                switch (NetworkConfig.IO_EXECUTION_MODE) {
                    case POOLED:
                        this.channelGroup = AsynchronousChannelGroup.withFixedThreadPool(NetworkConfig.IO_EXECUTION_THREAD_NUM, Executors.defaultThreadFactory());
                        break;

                    case FIXED:
                        this.channelGroup = AsynchronousChannelGroup.withThreadPool(ThreadPool.getInstance().getPacketExecutor());
                        break;
                     default:
                         break;
                }


            } catch (Exception exception) {
                log.error("Error while creating AsynchronousChannelGroup", exception);
            }
        }
        return this.channelGroup;
    }

    private InetSocketAddress getBindAddress() {
        if (this.bindAddress == null) {
            if (NetworkConfig.HOST.isEmpty() || NetworkConfig.HOST.equals("*")) {
                this.bindAddress = new InetSocketAddress(NetworkConfig.PORT);
            }
            this.bindAddress = new InetSocketAddress(NetworkConfig.HOST, NetworkConfig.PORT);
        }
        return this.bindAddress;
    }


    public IAcceptFilter getAcceptFilter() {
        return this.ACCEPT_FILTER;
    }


    public void startup() {
        try {
            this._future = this._bootstrap.bind(NetworkConfig.HOST, NetworkConfig.PORT).sync();
            this.isShutdown = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Waiting for connections on {}:{}", NetworkConfig.HOST, Integer.valueOf(NetworkConfig.PORT));
    }

    public void shutdown() {
        this._future.channel().close();
        try {
            this._future.channel().closeFuture().sync();
            this._workerLoopGroup.shutdownGracefully().sync();
            this._mainLoopGroup.shutdownGracefully().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isShutdown = true;
    }

    public boolean isShutdown() {
        return this.isShutdown;
    }

    AsynchronousServerSocketChannel getServerSocketChannel() {
        return this.serverSocketChannel;
    }

    @Override
    public String toString() {
        try {
            InetSocketAddress inetSocketAddress = (InetSocketAddress)getServerSocketChannel().getLocalAddress();
            return "{NetworkThread listening at " + inetSocketAddress.getHostName() + ":" + inetSocketAddress.getPort() + "}";
        }
        catch (Exception exception) {
            return "{NetworkThread}";
        }
    }
}
