package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName SMLoadUiInfo
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:33
 * VERSION 1.0
 */

public class SMLoadUiInfo extends SendablePacket<LoginClient> {
    private String uiInfo;

    public SMLoadUiInfo(final String uiInfo) {
        this.uiInfo = uiInfo;
    }

    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeC(1);
        sendByteBuffer.writeS((CharSequence)this.uiInfo, 1301);
    }
}
