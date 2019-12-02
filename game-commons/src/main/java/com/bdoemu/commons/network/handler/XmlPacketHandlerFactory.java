package com.bdoemu.commons.network.handler;

import com.bdoemu.commons.network.Client;
import com.bdoemu.commons.network.ClientState;
import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.commons.utils.ServerInfoUtils;
import com.bdoemu.commons.xml.XmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName XmlPacketHandlerFactory
 * @Description XML 包处理工厂
 * @Author JiangBangMing
 * @Date 2019/6/24 12:06
 * VERSION 1.0
 */

public class XmlPacketHandlerFactory<T extends Client<T>> extends AbstractPacketHandlerFactory<T>{
    private static final Logger log = LoggerFactory.getLogger(XmlPacketHandlerFactory.class);

    public XmlPacketHandlerFactory() {
        super(null, null);
        loadPackets();
    }

    private void loadPackets() {
        ServerInfoUtils.printSection("Loading packet definition's...");
        XmlParser protocol = new XmlParser("data/packets.xml", "Protocol");

        int serverPacketsCount = 0, clientPacketCount = 0;

        XmlParser clientPackets = protocol.child("ClientPackets");
        for (XmlParser clientPacket : clientPackets.children("Packet")) {
            String packetName = clientPacket.string("Name");
            short packetOpcode = (short)clientPacket.integerHex("Opcode");

            try {
                Set<ClientState> states = new HashSet<>();
                XmlParser stateNode = clientPacket.optChild("States");
                if (stateNode != null) {
                    Collection<XmlParser> stateNodes = stateNode.children();
                    states.addAll(stateNodes.stream().map(state -> ClientState.valueOf(state.name())).collect(Collectors.toList()));
                }
                if (states.isEmpty()) {
                    states.add(ClientState.CONNECTED);
                    states.add(ClientState.ENTERED);
                }
                final Class<? extends ReceivablePacket<T>> packetClass = (Class<? extends ReceivablePacket<T>>)Class.forName("com.bdoemu.core.network.receivable." + packetName);
                this.addPacket(packetClass.getDeclaredConstructor(Short.TYPE).newInstance(packetOpcode), (ClientState[])states.
                        toArray(new ClientState[states.size()]));
                ++clientPacketCount;
            }
            catch (NoSuchMethodException|IllegalAccessException|InstantiationException|java.lang.reflect.InvocationTargetException ex) {
                log.error("Error while creating client packet from XML definition's!", ex);
            }
            catch (ClassNotFoundException classNotFoundException) {

            }
        }

        XmlParser serverPackets = protocol.child("ServerPackets");
        for (XmlParser clientPacket : serverPackets.children("Packet")) {
            String packetName = clientPacket.string("Name");
            short packetOpcode = (short)clientPacket.integerHex("Opcode");

            try {
                final Class<? extends SendablePacket<T>> packetClass2 = (Class<? extends SendablePacket<T>>)Class.forName("com.bdoemu.core.network.sendable." + packetName);
                this.addPacket(packetClass2, packetOpcode);
                ++serverPacketsCount;
            }
            catch (ClassNotFoundException classNotFoundException) {}
        }

        log.info("Loaded {} server and {} client packet definition's.", Integer.valueOf(serverPacketsCount), Integer.valueOf(clientPacketCount));
    }


}
