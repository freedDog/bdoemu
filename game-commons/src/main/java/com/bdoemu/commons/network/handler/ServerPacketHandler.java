package com.bdoemu.commons.network.handler;

import com.bdoemu.commons.network.Client;
import com.bdoemu.commons.network.SendablePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ServerPacketHandler
 * @Description 服务器包 处理
 * @Author JiangBangMing
 * @Date 2019/6/22 15:33
 * VERSION 1.0
 */

public class ServerPacketHandler<TClient extends Client<TClient>> {

    private static final Logger log = LoggerFactory.getLogger(ServerPacketHandler.class);

    private final Map<Class<? extends SendablePacket<TClient>>, Short> opcodes = new HashMap();

    short getOpCode(Class<? extends SendablePacket> packetClass) {
        Short opcode = (Short)this.opcodes.get(packetClass);
        if (opcode == null) {
            log.error("Can't find opcode for packet: {}", packetClass.getSimpleName());
            return 0;
        }
        return opcode.shortValue();
    }

    void addPacketOpcode(Class<? extends SendablePacket<TClient>> packetClass, short opcode) {
        if (opcode < 0) {
            return;
        }

        if (this.opcodes.values().contains(Short.valueOf(opcode))) {
            throw new IllegalArgumentException(String.format("There already exists another packet with id 0x%02X", new Object[] { Short.valueOf(opcode) }));
        }

        this.opcodes.put(packetClass, Short.valueOf(opcode));
    }
}
