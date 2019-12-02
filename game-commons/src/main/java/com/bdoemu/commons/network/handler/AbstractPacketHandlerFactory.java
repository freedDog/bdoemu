package com.bdoemu.commons.network.handler;

import com.bdoemu.commons.network.Client;
import com.bdoemu.commons.network.ClientState;
import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.commons.network.SendablePacket;

/**
 * @ClassName AbstractPacketHandlerFactory
 * @Description 包处理工厂
 * @Author JiangBangMing
 * @Date 2019/6/22 15:28
 * VERSION 1.0
 */

public class AbstractPacketHandlerFactory<TClient extends Client<TClient>> {

    private ClientPacketHandler<TClient> cHandler;
    private ServerPacketHandler<TClient> sHandler;

    AbstractPacketHandlerFactory(ServerPacketHandler<TClient> sph, ClientPacketHandler<TClient> cph) {
        if (sph == null) {
            this.sHandler = new ServerPacketHandler();
        } else {
            this.sHandler = sph;
        }

        if (cph == null) {
            this.cHandler = new ClientPacketHandler();
        } else {
            this.cHandler = cph;
        }
    }


    void addPacket(Class<? extends SendablePacket<TClient>> packetClass, short opcode) {
        this.sHandler.addPacketOpcode(packetClass, opcode);
    }



    void addPacket(ReceivablePacket<TClient> packetPrototype, ClientState... states) {
        this.cHandler.addPacketPrototype(packetPrototype, states);
    }

    public short getServerPacketOpCode(Class<? extends SendablePacket> packetClass) {
        return this.sHandler.getOpCode(packetClass);
    }

    public short getClientPacketOpCode(Class<? extends ReceivablePacket> packetClass) {
        return this.cHandler.getOpcode(packetClass);
    }

    public ReceivablePacket<TClient> handleClientPacket(short id, TClient ch) {
        return this.cHandler.handle(id, ch);
    }

}
