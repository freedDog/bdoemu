package com.bdoemu.commons.network.handler;

import com.bdoemu.commons.network.Client;
import com.bdoemu.commons.network.ClientState;
import com.bdoemu.commons.network.ReceivablePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClientPacketHandler
 * @Description 客户端包处理
 * @Author JiangBangMing
 * @Date 2019/6/22 15:29
 * VERSION 1.0
 */

public class ClientPacketHandler<TClient extends Client<TClient>> {

    private static final Logger log;
    private final Map<ClientState, Map<Short, ReceivablePacket<TClient>>> packetsPrototypes;
    private final Map<Class<?>, Short> packetsOpcodes;

    ClientPacketHandler() {
        this.packetsPrototypes = new HashMap<>();
        this.packetsOpcodes = new HashMap<>();
    }

    void addPacketPrototype(final ReceivablePacket<TClient> packetPrototype, final ClientState... states) {
        for (final ClientState state : states) {
            final Map<Short, ReceivablePacket<TClient>> pm = this.packetsPrototypes.computeIfAbsent(state, k -> new HashMap());
            pm.put(packetPrototype.getOpCode(), packetPrototype);
        }
        if (!this.packetsOpcodes.containsKey(packetPrototype.getClass())) {
            this.packetsOpcodes.put(packetPrototype.getClass(), packetPrototype.getOpCode());
        }
    }

    private ReceivablePacket<TClient> getPacket(final short id, final TClient ch) {
        ReceivablePacket<TClient> prototype = null;
        final Map<Short, ReceivablePacket<TClient>> pm = this.packetsPrototypes.get(ch.getState());
        if (pm != null) {
            prototype = pm.get(id);
        }
        if (prototype == null) {
            unknownPacket(ch.getState(), id);
            return null;
        }
        return prototype.clonePacket();
    }

    public short getOpcode(final Class<?> packetClass) {
        return this.packetsOpcodes.get(packetClass);
    }

    public ReceivablePacket<TClient> handle(final short id, final TClient ch) {
        return this.getPacket(id, ch);
    }

    private static void unknownPacket(final ClientState state, final short id) {
        ClientPacketHandler.log.error("[UNKNOWN PACKET] : received 0x{}, state={}",Integer.toHexString(id), state.toString());
    }

    static {
        log = LoggerFactory.getLogger((Class)ClientPacketHandler.class);
    }
}
