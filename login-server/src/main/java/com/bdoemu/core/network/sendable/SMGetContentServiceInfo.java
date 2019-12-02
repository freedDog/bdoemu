package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.configs.LocalizingOptionConfig;
import com.bdoemu.core.configs.NetworkConfig;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName SMGetContentServiceInfo
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:24
 * VERSION 1.0
 */

public class SMGetContentServiceInfo extends SendablePacket<LoginClient> {

    public SMGetContentServiceInfo() {
        if (NetworkConfig.ENCRYPT_PACKETS) {
            this.setEncrypt(true);
        }
    }
    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeC(4);
        sendByteBuffer.writeC(LocalizingOptionConfig.DEFAULT_CHARACTER_SLOT);
        sendByteBuffer.writeC(LocalizingOptionConfig.CHARACTER_SLOT_LIMIT);
        sendByteBuffer.writeC(0);
        sendByteBuffer.writeC(0);
        sendByteBuffer.writeQ(3000000L);
        sendByteBuffer.writeD(300000);
        sendByteBuffer.writeD(0);
        sendByteBuffer.writeD(LocalizingOptionConfig.ITEM_MARKET_REFUND_PERCENT_FOR_PREMIUM_PACKAGE);
        sendByteBuffer.writeD(LocalizingOptionConfig.ITEM_MARKET_REFUND_PERCENT_FOR_PCROOM_AND_PREMIUM_PACKAGE);
        sendByteBuffer.writeQ((long)LocalizingOptionConfig.BIDDING_TIME);
        LocalizingOptionConfig.POSSIBLE_CLASS.forEach(sendByteBuffer::writeC);
        for (int i = 0; i < 32 - LocalizingOptionConfig.POSSIBLE_CLASS.size(); ++i) {
            sendByteBuffer.writeC(32);
        }
        sendByteBuffer.writeD(0);
        sendByteBuffer.writeD(LocalizingOptionConfig.CHARACTER_REMOVE_TIME_CHECK_LEVEL);
        sendByteBuffer.writeQ((long)LocalizingOptionConfig.LOW_LEVEL_CHARACTER_REMOVE_TIME);
        sendByteBuffer.writeQ((long)LocalizingOptionConfig.CHARACTER_REMOVE_TIME);
        sendByteBuffer.writeQ((long)LocalizingOptionConfig.NAME_REMOVE_TIME);
        sendByteBuffer.writeC(LocalizingOptionConfig.GUILD_WAR_TYPE.ordinal());
        sendByteBuffer.writeC(LocalizingOptionConfig.CAN_MAKE_GUILD);
        sendByteBuffer.writeC(LocalizingOptionConfig.CAN_REGISTER_PEARL_ITEM_ON_MARKET);
        sendByteBuffer.writeH(11);
        sendByteBuffer.writeC(0);
        sendByteBuffer.writeD(150);
        sendByteBuffer.writeC(LocalizingOptionConfig.OPENED_DESERT_PK);
        sendByteBuffer.writeH(LocalizingOptionConfig.ACCESSIBLE_REGION_GROUP_KEY.size());
        for (final Integer n : LocalizingOptionConfig.ACCESSIBLE_REGION_GROUP_KEY) {
            sendByteBuffer.writeC(4);
            sendByteBuffer.writeH((int)n);
        }
    }
}
