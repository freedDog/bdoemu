package com.bdoemu.core.configs;

import com.bdoemu.commons.config.annotation.ConfigAfterLoad;
import com.bdoemu.commons.config.annotation.ConfigComments;
import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;
import com.bdoemu.commons.network.enums.ENetPacketExecMode;
import com.bdoemu.commons.network.enums.ENetIOExecMode;

/**
 * @ClassName NetworkConfig
 * @Description 网络配置文件
 * @Author JiangBangMing
 * @Date 2019/6/22 11:14
 * VERSION 1.0
 */

@ConfigFile(name="configs/network.properties")
public class NetworkConfig {
    @ConfigComments(comment = {"Rmi connection host."})
    @ConfigProperty(name = "network.rmi.host", value = "0.0.0.0")
    public static String RMI_HOST;
    @ConfigComments(comment = {"Rmi connection port."})
    @ConfigProperty(name = "network.rmi.port", value = "9000")
    public static int RMI_PORT;
    @ConfigComments(comment = {"Rmi connection password."})
    @ConfigProperty(name = "network.rmi.password", value = "1234")
    public static String RMI_PASSWORD;
    @ConfigComments(comment = {"Server version supported", "NA-EU: 4635"})
    @ConfigProperty(name = "network.server.version", value = "4635")
    public static int SERVER_VERSION;
    @ConfigComments(comment = {"Client support european."})
    @ConfigProperty(name = "network.encrypt.packets", value = "true")
    public static boolean ENCRYPT_PACKETS;
    @ConfigComments(comment = {"Host for server binding.", "Default: 0.0.0.0"})
    @ConfigProperty(name = "network.host", value = "0.0.0.0")
    public static String HOST;
    @ConfigComments(comment = {"Port for server binding.", "Default: 8888"})
    @ConfigProperty(name = "network.port", value = "8888")
    public static int PORT;
    @ConfigComments(comment = {"Show debug messages.", "Default: false"})
    @ConfigProperty(name = "network.debug", value = "false")
    public static boolean DEBUG;
    @ConfigComments(comment = {"Packet executing mode.", "DIRECT - The worst. Income packets are directly executed after receiving and decrypting. As packets here implemented terribly, it not only increases latency, but decrease overall performance significant.", "OFFLOAD - Offloads execution to a ThreadPool. Good option with POOLED IOExec mode. Increases throughput but also may increase context switch count.", "Default: OFFLOAD"})
    @ConfigProperty(name = "network.packet.exec.mode", value = "OFFLOAD")
    public static ENetPacketExecMode PACKET_EXEC_MODE;
    @ConfigComments(comment = {"Receive buffer size.", "Default: 32768"})
    @ConfigProperty(name = "network.recv.buffer.size", value = "32768")
    public static int RECV_BUFFER_SIZE;
    @ConfigComments(comment = {"Send buffer size.", "Default: 65536"})
    @ConfigProperty(name = "network.send.buffer.size", value = "65536")
    public static int SEND_BUFFER_SIZE;
    @ConfigComments(comment = {"NetworkThread socket backlog size.", "See: http://www.linuxjournal.com/files/linuxjournal.com/linuxjournal/articles/023/2333/2333s2.html", "Default: 50"})
    @ConfigProperty(name = "network.server.socket.backlog", value = "50")
    public static int SERVER_SOCKET_BACKLOG;
    @ConfigComments(comment = {"Client socket options.", "SO_SNDBUF - the size of the socket's send buffer. On most systems this the size of a kernel buffer so be careful! See RFC1323.", "SO_RCVBUF - the size of the socket's receive buffer. On most systems this the size of a kernel buffer so be careful! See RFC1323.", "TCP_NODELAY - The Nagle algorithm. Enabling it increases throughput but also increases latency. See RFC1122.", "Default: SO_SNDBUF(8192);SO_RCVBUF(8192);TCP_NODELAY(true)"})
    @ConfigProperty(name = "network.client.socket.options", value = "SO_SNDBUF(8192);SO_RCVBUF(8192);TCP_NODELAY(true)")
    public static String CLIENT_SOCKET_OPTIONS;
    @ConfigComments(comment = {"NetworkThread socket options.", "SO_REUSEADDR - if true, prevents socket from usage until all opened sockets are really closed. See RFC793.", "Default: SO_REUSEADDR(true);SO_RCVBUF(4096)"})
    @ConfigProperty(name = "network.server.socket.options", value = "SO_REUSEADDR(false);SO_RCVBUF(4096)")
    public static String SERVER_SOCKET_OPTIONS;
    @ConfigComments(comment = {"IO Network thread execution mode.", "POOLED - All IO operations are executed in a special thread IO execution pool", "FIXED - All IO operations execution is spread across fixed number of treads", "Default: POOLED"})
    @ConfigProperty(name = "network.io.execution.mode", value = "POOLED")
    public static ENetIOExecMode IO_EXECUTION_MODE;
    @ConfigComments(comment = {"Number of IO Network threads.", "Default: -1 (Processor count)"})
    @ConfigProperty(name = "network.io.execution.thread.num", value = "-1")
    public static int IO_EXECUTION_THREAD_NUM;
    @ConfigComments(comment = {"Income packet header size.", "Default: 3"})
    @ConfigProperty(name = "network.income.packet.header.size", value = "3")
    public static byte INCOME_PACKET_HEADER_SIZE;
    @ConfigComments(comment = {"Max income packet size.", "Default: 16384"})
    @ConfigProperty(name = "network.max.income.packet.size", value = "16384")
    public static int MAX_INCOME_PACKET_SIZE;
    @ConfigComments(comment = {"Outcome packet header size.", "Default: 3"})
    @ConfigProperty(name = "network.outcome.packet.header.size", value = "3")
    public static byte OUTCOME_PACKET_HEADER_SIZE;
    @ConfigComments(comment = {"Max outcome packet size.", "Default: 16384"})
    @ConfigProperty(name = "network.max.outcome.packet.size", value = "16384")
    public static int MAX_OUTCOME_PACKET_SIZE;

    @ConfigAfterLoad
    private void afterLoad() {
        if (IO_EXECUTION_THREAD_NUM < 0) {
            IO_EXECUTION_THREAD_NUM = Runtime.getRuntime().availableProcessors();
        }
    }
}
