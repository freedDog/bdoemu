package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.ICipher;
import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName SMSetFrameworkInformation
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 10:41
 * VERSION 1.0
 */

public class SMSetFrameworkInformation extends SendablePacket<LoginClient> {
    private final ICipher crypt;

    public SMSetFrameworkInformation(final ICipher crypt) {
        this.crypt = crypt;
        this.setEncrypt(true);
    }
    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeB(this.crypt.getFrameWorkData());
    }
}
