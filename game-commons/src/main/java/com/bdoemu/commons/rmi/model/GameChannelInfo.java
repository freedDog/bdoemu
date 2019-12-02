package com.bdoemu.commons.rmi.model;

import com.bdoemu.commons.model.enums.EServerBusyState;
import com.bdoemu.commons.rmi.IGameServerRMI;
import com.bdoemu.commons.utils.XMLNode;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.Serializable;

/**
 * @ClassName GameChannelInfo
 * @Description 游戏通道信息
 * @Author JiangBangMing
 * @Date 2019/6/21 20:48
 * VERSION 1.0
 */

public class GameChannelInfo extends XMLNode implements Serializable {

    private int serverId;
    private int channelId;
    private int channelPosition;
    private String ip;
    private int port;
    private String name;
    private String channelName;
    private String password;

    public GameChannelInfo(Node node) {
        NamedNodeMap a = node.getAttributes();
        this.serverId = readD(a, "id");
        this.channelId = readD(a, "channelId");
        this.channelPosition = readD(a, "channelPosition");
        this.name = readS(a, "name");
        this.channelName = readS(a, "channelName");
        this.ip = readS(a, "ip");
        this.port = readD(a, "port");
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }
    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
    public void setChannelPosition(int channelPosition) {
        this.channelPosition = channelPosition;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setServerBusyState(EServerBusyState serverBusyState) {
        this.serverBusyState = serverBusyState;
    }
    public void setConnection(IGameServerRMI connection) {
        this.connection = connection;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GameChannelInfo)){
            return false;
        }
        GameChannelInfo other = (GameChannelInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (getServerId() != other.getServerId()) {
            return false;
        }
        if (getChannelId() != other.getChannelId()){
            return false;
        }
        if (getChannelPosition() != other.getChannelPosition()) {
            return false;
        }
        Object this$ip = getIp(), other$ip = other.getIp();
        if ((this$ip == null) ? (other$ip != null) : !this$ip.equals(other$ip)){
            return false;
        }
        if (getPort() != other.getPort()){
            return false;
        }
        Object this$name = getName(), other$name = other.getName();
        if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name)) {
            return false;
        }
        Object this$channelName = getChannelName(), other$channelName = other.getChannelName();
        if ((this$channelName == null) ? (other$channelName != null) : !this$channelName.equals(other$channelName)) {
            return false;
        }
        Object this$password = getPassword(), other$password = other.getPassword();
        if ((this$password == null) ? (other$password != null) : !this$password.equals(other$password)){
            return false;
        }
        Object this$serverBusyState = getServerBusyState(), other$serverBusyState = other.getServerBusyState();
        if ((this$serverBusyState == null) ? (other$serverBusyState != null) : !this$serverBusyState.equals(other$serverBusyState)){
            return false;
        }
        Object this$connection = getConnection(), other$connection = other.getConnection();
        return !((this$connection == null) ? (other$connection != null) : !this$connection.equals(other$connection));
    }
    protected boolean canEqual(Object other) {
        return other instanceof GameChannelInfo;
    }
    @Override
    public int hashCode() {
        int PRIME = 59, result = 1;
        result = result * 59 + getServerId();
        result = result * 59 + getChannelId();
        result = result * 59 + getChannelPosition();
        Object $ip = getIp();
        result = result * 59 + (($ip == null) ? 43 : $ip.hashCode());
        result = result * 59 + getPort();
        Object $name = getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        Object $channelName = getChannelName();
        result = result * 59 + (($channelName == null) ? 43 : $channelName.hashCode());
        Object $password = getPassword();
        result = result * 59 + (($password == null) ? 43 : $password.hashCode());
        Object $serverBusyState = getServerBusyState();
        result = result * 59 + (($serverBusyState == null) ? 43 : $serverBusyState.hashCode());
        Object $connection = getConnection(); return result * 59 + (($connection == null) ? 43 : $connection.hashCode());
    }

    public int getServerId() {
        return this.serverId;
    }
    public int getChannelId() {
        return this.channelId;
    }
    public int getChannelPosition() {
        return this.channelPosition;
    }
    public String getIp() {
        return this.ip;
    }
    public int getPort() {
        return this.port;
    }
    public String getName() {
        return this.name;
    }
    public String getChannelName() {
        return this.channelName;
    }
    public String getPassword() {
        return this.password;
    }
    private EServerBusyState serverBusyState = EServerBusyState.INSPECTION;
    private IGameServerRMI connection;
    public EServerBusyState getServerBusyState() {
        return this.serverBusyState;
    }

    public GameChannelInfo() {
    }

    public IGameServerRMI getConnection() {
        return this.connection;
    }

    public GameChannelInfo(int serverId, int channelId) {
        this.serverId = serverId;
        this.channelId = channelId;
    }

    public void update(IGameServerRMI connection, GameChannelInfo channelInfo) {
        this.connection = connection;
        this.ip = channelInfo.getIp();
        this.port = channelInfo.getPort();
        this.serverBusyState = channelInfo.getServerBusyState();
    }

    @Override
    public String toString() {
        return "ID=" + this.serverId + " ChannelID=" + this.channelId + " Status=" + this.serverBusyState.toString();
    }
}
