package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.configs.NetworkConfig;
import com.bdoemu.core.network.LoginClient;
import com.bdoemu.login.models.db.Account;

/**
 * @ClassName SMLoginUserToAuthenticServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:33
 * VERSION 1.0
 */

public class SMLoginUserToAuthenticServer extends SendablePacket<LoginClient> {
    private final Account account;
    private final int cookie;

    public SMLoginUserToAuthenticServer(final Account account, final int cookie) {
        if (NetworkConfig.ENCRYPT_PACKETS) {
            this.setEncrypt(true);
        }
        this.account = account;
        this.cookie = cookie;
    }
    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeD(this.cookie);
        sendByteBuffer.writeC(this.account.getCharacterSlots());
        sendByteBuffer.writeC(1);
        sendByteBuffer.writeD(NetworkConfig.SERVER_VERSION);
    }

}
