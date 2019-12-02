package com.bdoemu.commons.network;

import java.nio.ByteBuffer;

/**
 * @ClassName ICipher
 * @Description 密码
 * @Author JiangBangMing
 * @Date 2019/6/22 12:20
 * VERSION 1.0
 */

public interface ICipher {

    boolean encrypt(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);

    boolean decrypt(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);

    byte[] getFrameWorkData();
}
