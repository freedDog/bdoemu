package com.bdoemu.commons.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName SendablePacket
 * @Description 发送数据包
 * @Author JiangBangMing
 * @Date 2019/6/22 13:47
 * VERSION 1.0
 */

public abstract class SendablePacket<TClient extends Client<TClient>>{

    private static final Logger log = LoggerFactory.getLogger(SendablePacket.class);
    private boolean encrypt = false;

    public boolean isEncrypt() {
        return this.encrypt;
    }
    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    protected boolean write(TClient client, SendByteBuffer buffer) {
        try {
            writeOpCode(client, buffer);
            writeBody(buffer);
            return true;
        }
        catch (Exception e) {
            log.error("Error while write packet [{}]", getClass().getSimpleName(), e);
            return false;
        }
    }


    protected void writeOpCode(TClient client, SendByteBuffer buffer) {
        buffer.writeH(client.getPacketHandler().getServerPacketOpCode(getClass()));
    }

    protected abstract void writeBody(SendByteBuffer paramSendByteBuffer);
}
