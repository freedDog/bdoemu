package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName SMRegisterNickNameToAuthenticServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:37
 * VERSION 1.0
 */

public class SMRegisterNickNameToAuthenticServer extends SendablePacket<LoginClient> {

    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeD(26403848);
    }
}
