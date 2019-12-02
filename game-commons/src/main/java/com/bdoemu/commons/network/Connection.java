package com.bdoemu.commons.network;


import com.bdoemu.commons.thread.ThreadPool;
import com.bdoemu.core.configs.NetworkConfig;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName Connection
 * @Description 连接
 * @Author JiangBangMing
 * @Date 2019/6/22 12:05
 * VERSION 1.0
 */

public class Connection<TClient extends Client<TClient>> extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(Connection.class);
    private ICipher cipher;
    private TClient client;
    private Channel channel;
    private NetworkThread<TClient> server;
    private AtomicReference<EConnectionState> connectionState;

    public void init(){

    }

    public Connection(NetworkThread<TClient> paramNetworkThread, AsynchronousSocketChannel channel, InetSocketAddress socketAddress) {
        this.server = paramNetworkThread;
        this.connectionState = new AtomicReference(EConnectionState.OPEN);
    }

    public Connection(NetworkThread<TClient> paramNetworkThread) {
        this.server = paramNetworkThread;
        this.connectionState = new AtomicReference(EConnectionState.OPEN);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext paramChannelHandlerContext) {
        this.client = this.server.getClientFactory().createClient(this);
    }
    @Override
    public void channelActive(ChannelHandlerContext paramChannelHandlerContext) {
        this.channel = paramChannelHandlerContext.channel();
        this.client.onOpen();
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext paramChannelHandlerContext) {
        close();
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext paramChannelHandlerContext) {
        paramChannelHandlerContext.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext paramChannelHandlerContext, Object paramObject) {
        if (paramObject instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf)paramObject;
            int i = byteBuf.readableBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(i).order(ByteOrder.LITTLE_ENDIAN);
            byteBuf.readBytes(byteBuffer);
            byteBuf.release();
            byteBuffer.position(0);

            int j = byteBuffer.position();
            int k = byteBuffer.limit();
            int m = j + NetworkConfig.INCOME_PACKET_HEADER_SIZE;
            int n = i - NetworkConfig.INCOME_PACKET_HEADER_SIZE;
            if (i < 5) {
                log.warn("Received packet without correct header. Length: " + i);
                return;
            }
            try {
                Client<TClient> client = this.getClient();;
                byteBuffer.limit(m + n);
                short s1 = byteBuffer.getShort();
                boolean bool = (byteBuffer.get() == 1) ? true : false;
                if (bool) {
                    getCipher().decrypt(byteBuffer, m, n);
                }
                byteBuffer.position(m);
                short opCode = byteBuffer.getShort();
                ReceivablePacket receivablePacket = getServer().getPacketHandler().handleClientPacket(opCode, (TClient) client);
                if (receivablePacket != null) {
                    if (NetworkConfig.DEBUG) {
                        log.warn("Client -> Server: {}", receivablePacket.getClass().getSimpleName());
                    }
                    receivablePacket.setClient(client);
                    receivablePacket.setBuffer(byteBuffer);
                    try {
                        receivablePacket.read();
                        receivablePacket.run();
                    } catch (Exception exception) {
                        log.error("Created packet [{}] has not been read.", receivablePacket.getClass().getSimpleName(), exception);
                        close();
                    }
                } else {
                    log.error("Packet wasn't found so... closing..");
                    close();
                }
            } catch (Exception exception) {
                log.error("Packed read failed: connectionIsClosed={} packetBodyPos={} packetBodySize={} _bufferPosition={} _bufferLimit={} _bufferOrigLimit={}", new Object[] { Boolean.valueOf(isClosed()), Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(byteBuffer.position()), Integer.valueOf(byteBuffer.limit()), Integer.valueOf(k), exception });
                close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext paramChannelHandlerContext, Throwable paramThrowable) {
        if (paramThrowable instanceof io.netty.handler.timeout.ReadTimeoutException || paramThrowable instanceof java.io.IOException) {
            if (paramThrowable instanceof io.netty.handler.timeout.ReadTimeoutException) {
                log.error("User has been disconnected due ReadTimeoutException.");
            }
            close();
        } else {
            log.error("An exception occured when reading a packet.", new Exception(paramThrowable));
            close();
        }
    }

    private void writePacket(SendablePacket<TClient> paramSendablePacket, boolean paramBoolean1, boolean paramBoolean2) {
        SendByteBuffer sendByteBuffer = new SendByteBuffer();

        if (NetworkConfig.DEBUG) {
            log.warn("Server -> Client: {}", paramSendablePacket.getClass().getSimpleName());
        }
        Client<TClient> client = this.getClient();
        int i = sendByteBuffer.position();
        int j = i + NetworkConfig.OUTCOME_PACKET_HEADER_SIZE;
        sendByteBuffer.position(j);
        if (!paramSendablePacket.write((TClient) client, sendByteBuffer)) {
            return;
        }
        int k = sendByteBuffer.position();
        int m = k - j;
        sendByteBuffer.position(i);
        sendByteBuffer.writeH(m + NetworkConfig.OUTCOME_PACKET_HEADER_SIZE);
        sendByteBuffer.writeC(paramSendablePacket.isEncrypt());
        if (paramSendablePacket.isEncrypt()) {
            try {
                getCipher().encrypt(sendByteBuffer.getBuffer(), j, m);
                sendByteBuffer.position(k);
                sendByteBuffer.flip();
            }
            catch (Exception exception) {
                log.warn("Packet [{}] encrypt failed.", paramSendablePacket.getClass().getSimpleName());

                return;
            }
        }

        byte[] arrayOfByte = new byte[m + NetworkConfig.OUTCOME_PACKET_HEADER_SIZE];


        sendByteBuffer.setBuffer(sendByteBuffer.getBuffer().order(ByteOrder.BIG_ENDIAN));
        sendByteBuffer.position(0);
        sendByteBuffer.getBuffer().get(arrayOfByte, 0, m + NetworkConfig.OUTCOME_PACKET_HEADER_SIZE);


        sendByteBuffer.clear();
        sendByteBuffer = null;

        if (paramBoolean1 || paramBoolean2) {
            if (paramBoolean2) {
                try {
                    this.channel.writeAndFlush(arrayOfByte).await();
                }
                catch (InterruptedException interruptedException) {

                }
                ThreadPool.getInstance().scheduleNetwork(() ->
                        close(), 500L, TimeUnit.MILLISECONDS);
            } else {

                this.channel.writeAndFlush(arrayOfByte);
            }
        } else {
            this.channel.write(arrayOfByte);
        }

    }

    public void sendPacket(SendablePacket<TClient> paramSendablePacket) {
        writePacket(paramSendablePacket, true, false);
    }

    public void sendPacketNoFlush(SendablePacket<TClient> paramSendablePacket) {
        writePacket(paramSendablePacket, false, false);
    }

    public InetSocketAddress getSocketAddress() {
        return (this.channel != null) ? (InetSocketAddress)this.channel.remoteAddress() : null;
    }

    public TClient getClient() {
        return this.client;
    }

    public NetworkThread<TClient> getServer() {
        return this.server;
    }

    ICipher getCipher() {
        return this.cipher;
    }

    public void setCipher(ICipher paramICipher) {
        this.cipher = paramICipher;
    }

    protected void close() {
        if ((this.connectionState.compareAndSet(EConnectionState.OPEN, EConnectionState.CLOSED) || this.connectionState
                .compareAndSet(EConnectionState.PENDING_CLOSE, EConnectionState.CLOSED)) &&
                this.client != null) {
            this.client.onClose();
        }

        if (this.channel != null) {
            this.channel.close();
        }
    }

    public void sendAndClose(SendablePacket<TClient> paramSendablePacket) {
        if (paramSendablePacket == null) {
            close();
        } else {
            writePacket(paramSendablePacket, true, true);
        }
    }

    public boolean isClosedOrPending() {
        switch (this.connectionState.get()) {
            case PENDING_CLOSE:
            case CLOSED:
                return true;
            default:
                return false;
        }
    }

    public int getConnectionId() {
        return this.channel.id().hashCode();
    }

    protected boolean isClosed() {
        return (this.channel != null && (!this.channel.isOpen() || !this.channel.isActive()));
    }

    boolean isPendingClose() {
        return (this.connectionState.get() == EConnectionState.PENDING_CLOSE);
    }

    @Override
    public String toString() {
        return "{Connection from " + getSocketAddress().getHostString() + ":" + getSocketAddress().getPort() + "}";
    }

    /**
     * 连接状态
     */
    private enum EConnectionState {
        /***/
        OPEN,
        PENDING_CLOSE,
        CLOSED;
    }
}
