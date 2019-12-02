package com.bdoemu.commons.network;

import com.bdoemu.commons.network.handler.AbstractPacketHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName Client
 * @Description 客户端抽象类
 * @Author JiangBangMing
 * @Date 2019/6/22 12:03
 * VERSION 1.0
 */

public abstract class Client<TClient extends Client<TClient>> {
    private static final Logger log = LoggerFactory.getLogger("Network");
    private final Connection<TClient> connection;
    private final AtomicReference<ClientState> state;

    public Client(Connection<TClient> paramConnection) {
        this.state = new AtomicReference(ClientState.NULL);
        this.connection = paramConnection;
    }

    public Connection<TClient> getConnection() {
        return this.connection;
    }

    public int getConnectionId() {
        return getConnection().getConnectionId();
    }

    public boolean isConnected() {
        return !getConnection().isClosedOrPending();
    }

    protected void onOpen() {
        if (this.state.compareAndSet(ClientState.NULL, ClientState.CONNECTED)) {
            log.info("Client [{}] connected.", this);
        }
    }

    protected void onClose() {
        if (this.state.compareAndSet(ClientState.CONNECTED, ClientState.DISCONNECTED) || this.state
                .compareAndSet(ClientState.ENTERED, ClientState.DISCONNECTED))
        {
            log.info("Client [{}] disconnected.", this);
        }
    }

    public void closeForce() {
        getConnection().sendAndClose(null);
    }


    public void sendPacket(SendablePacket<TClient> paramSendablePacket) {
        if (!isConnected() || paramSendablePacket == null) {
            return;
        }
        getConnection().sendPacket(paramSendablePacket);
    }

    public void sendPacketNoFlush(SendablePacket<TClient> paramSendablePacket) {
        if (!isConnected() || paramSendablePacket == null) {
            return;
        }
        getConnection().sendPacketNoFlush(paramSendablePacket);
    }

    public void close(SendablePacket<TClient> paramSendablePacket) {
        if (!isConnected()) {
            return;
        }
        getConnection().sendAndClose(paramSendablePacket);
    }

    public boolean compareAndSetState(ClientState paramClientState1, ClientState paramClientState2) {
        return this.state.compareAndSet(paramClientState1, paramClientState2);
    }

    public ClientState getState() {
        return this.state.get();
    }

    public String getHostAddress() {
        if (isConnected()) {
            return getConnection().getSocketAddress().getAddress().getHostAddress();
        }
        return "not connected";
    }


    public AbstractPacketHandlerFactory<TClient> getPacketHandler() {
        return this.connection.getServer().getPacketHandler();
    }
    @Override
    public String toString() {

        switch (this.state.get()) {
            case CONNECTED:
            case AUTHED_GG:
            case AUTHED:
            case ENTERED:
                return "IP: " + getHostAddress() + " State: " + this.state.get();

            case NULL:
            case DISCONNECTED:
                return "";
                default:
                    return "";
        }
    }

}
