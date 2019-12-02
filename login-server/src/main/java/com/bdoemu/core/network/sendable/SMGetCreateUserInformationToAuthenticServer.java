package com.bdoemu.core.network.sendable;

import com.bdoemu.commons.network.SendByteBuffer;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.commons.utils.Rnd;
import com.bdoemu.core.configs.NetworkConfig;
import com.bdoemu.core.network.LoginClient;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassName SMGetCreateUserInformationToAuthenticServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:27
 * VERSION 1.0
 */

public class SMGetCreateUserInformationToAuthenticServer extends SendablePacket<LoginClient> {
    private LoginClient client;

    public SMGetCreateUserInformationToAuthenticServer(final LoginClient client) {
        this.client = client;
        if (NetworkConfig.ENCRYPT_PACKETS) {
            this.setEncrypt(true);
        }
    }

    @Override
    protected void writeBody(final SendByteBuffer sendByteBuffer) {
        sendByteBuffer.writeS(Long.toString(this.client.getAccount().getObjectId()), 62);
        sendByteBuffer.writeC(1);
        sendByteBuffer.writeC(0);
        sendByteBuffer.writeC(0);
        sendByteBuffer.writeC(64);
        sendByteBuffer.writeC(this.client.getAccount().getPin().isEmpty());
        this.generatePinTable(sendByteBuffer);
    }

    private void generatePinTable(final SendByteBuffer sendByteBuffer) {
        final int value = Rnd.get(20);
        sendByteBuffer.writeC(value);
        sendByteBuffer.writeC(0);
        sendByteBuffer.writeC(0);
        final int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (int i = 0; i < 20; ++i) {
            final List<? super Integer> list = IntStream.of(array).boxed().collect((Collectors.toList()));
            final StringBuilder sb = new StringBuilder();
            while (!list.isEmpty()) {
                sb.append(list.remove(Rnd.get(list.size())));
            }
            if (value == i) {
                this.client.setPinTable(sb.toString());
            }
            sendByteBuffer.writeQ((long)Long.parseLong(sb.toString()));
        }
    }
}
