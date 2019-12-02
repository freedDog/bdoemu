package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName SMLoadGameOption
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:32
 * VERSION 1.0
 */

public class SMLoadGameOption extends SendablePacket<LoginClient> {
    private String gameOptions;

    public SMLoadGameOption(final String gameOptions) {
        this.gameOptions = gameOptions;
    }

    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeS((CharSequence)this.gameOptions, 201);
    }
}
