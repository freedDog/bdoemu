package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.model.enums.EStringTable;
import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName SMLoginUserToAuthenticServerNak
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:36
 * VERSION 1.0
 */

public class SMLoginUserToAuthenticServerNak extends SendablePacket<LoginClient> {
    private EStringTable messageId;
    private EStringTable reasonId;

    public SMLoginUserToAuthenticServerNak(final EStringTable messageId, final EStringTable reasonId) {
        this.messageId = messageId;
        this.reasonId = reasonId;
    }

    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeS((CharSequence)"", 402);
        sendByteBuffer.writeQ(-2L);
        sendByteBuffer.writeD(this.messageId.getHash());
        sendByteBuffer.writeD(this.reasonId.getHash());
    }
}
