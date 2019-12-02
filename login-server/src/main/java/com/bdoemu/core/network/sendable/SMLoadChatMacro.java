package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.commons.rmi.model.Macros;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName SMLoadChatMacro
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:31
 * VERSION 1.0
 */

public class SMLoadChatMacro extends SendablePacket<LoginClient> {
    private Macros[] macroses;

    public SMLoadChatMacro(final Macros[] macroses) {
        this.macroses = macroses;
    }

    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeH(this.macroses.length);
        for (final Macros macros : this.macroses) {
            sendByteBuffer.writeD(macros.getIndex());
            sendByteBuffer.writeC((int)macros.getChatType());
            sendByteBuffer.writeS((CharSequence)macros.getMacrosData(), 702);
        }
    }
}
