package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.model.enums.EStringTable;
import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName SMNak
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:37
 * VERSION 1.0
 */

public class SMNak extends SendablePacket<LoginClient> {
    private long messageHash;
    private int callerPacketOpcode;

    public SMNak(final EStringTable eStringTable) {
        this.messageHash = eStringTable.getHash();
        this.callerPacketOpcode = 0;
    }

    public SMNak(final EStringTable eStringTable, final int callerPacketOpcode) {
        this.messageHash = eStringTable.getHash();
        this.callerPacketOpcode = callerPacketOpcode;
    }

    public SMNak(final int n, final int callerPacketOpcode) {
        this.messageHash = n;
        this.callerPacketOpcode = callerPacketOpcode;
    }

    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeD(this.messageHash);
        sendByteBuffer.writeH(this.callerPacketOpcode);
        sendByteBuffer.writeQ(0L);
    }
}
