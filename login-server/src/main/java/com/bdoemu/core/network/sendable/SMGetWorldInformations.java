package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.commons.rmi.model.GameAccountInfo;
import com.bdoemu.commons.rmi.model.GameChannelInfo;
import com.bdoemu.core.configs.LoginConfig;
import com.bdoemu.core.configs.NetworkConfig;
import com.bdoemu.core.network.LoginClient;
import com.bdoemu.login.LoginServer;
import com.bdoemu.login.models.db.Account;

import java.util.List;

/**
 * @ClassName SMGetWorldInformations
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:30
 * VERSION 1.0
 */

public class SMGetWorldInformations extends SendablePacket<LoginClient> {
    private final Account account;

    public SMGetWorldInformations(final Account account) {
        if (NetworkConfig.ENCRYPT_PACKETS) {
            this.setEncrypt(true);
        }
        this.account = account;
    }

    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        final List<GameChannelInfo> channels = LoginServer.getRmi().getChannels();
        sendByteBuffer.writeD(0);
        sendByteBuffer.writeQ(System.currentTimeMillis() / 1000L);
        sendByteBuffer.writeC(1);
        sendByteBuffer.writeH(channels.size());
        for (final GameChannelInfo gameChannelInfo : channels) {
            GameAccountInfo gameAccountInfo;
            try {
                gameAccountInfo = gameChannelInfo.getConnection().getGameAccountInfo(this.account.getObjectId());
            }
            catch (Exception ex) {
                gameAccountInfo = new GameAccountInfo(0, 0);
            }
            sendByteBuffer.writeH(gameChannelInfo.getChannelId());
            sendByteBuffer.writeH(gameChannelInfo.getServerId());
            sendByteBuffer.writeH(gameChannelInfo.getChannelPosition());
            sendByteBuffer.writeH(0);
            sendByteBuffer.writeH(4);
            sendByteBuffer.writeS((CharSequence)gameChannelInfo.getChannelName(), 62);
            sendByteBuffer.writeS((CharSequence)gameChannelInfo.getName(), 62);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writes((CharSequence)gameChannelInfo.getIp(), 62);
            sendByteBuffer.writeB(new byte[39]);
            sendByteBuffer.writeH(gameChannelInfo.getPort());
            sendByteBuffer.writeC(gameChannelInfo.getServerBusyState().ordinal());
            sendByteBuffer.writeC(1);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writeC(gameAccountInfo.getCharactersCount());
            sendByteBuffer.writeC(gameAccountInfo.getCharactersForDelete());
            sendByteBuffer.writeH(0);
            sendByteBuffer.writeQ(0L);
            sendByteBuffer.writeQ(System.currentTimeMillis() / 1000L);
            sendByteBuffer.writeC((int) LoginConfig.SERVER_BONUSES_VALUE);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writeH(0);
            sendByteBuffer.writeD(2000000);
            sendByteBuffer.writeS((CharSequence)"", 62);
            sendByteBuffer.writeD(1000000);
            sendByteBuffer.writeD(1000000);
            sendByteBuffer.writeC(LoginConfig.SERVER_GOLDEN_MEDAL);
            sendByteBuffer.writeH(0);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writeC(0);
            sendByteBuffer.writeC(0);
        }
    }
}
