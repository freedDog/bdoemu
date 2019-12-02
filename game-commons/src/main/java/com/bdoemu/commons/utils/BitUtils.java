package com.bdoemu.commons.utils;

import java.nio.ByteBuffer;

/**
 * @ClassName BitUtils
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 15:26
 * VERSION 1.0
 */

public class BitUtils {
    public static long HIDWORD(final long value, final int i) {
        return (value & 0xFFFFFFFFL) | ((long)i << 32 & 0xFFFFFFFF00000000L);
    }

    public static long LODWORD(final long value, final int i) {
        return (value & 0xFFFFFFFF00000000L) | ((long)i & 0xFFFFFFFFL);
    }

    public static int HIDWORD(final long value) {
        return (int)(value >> 32);
    }

    public static int LODWORD(final long value) {
        return (int)value;
    }

    public static int getArrInt(final ByteBuffer buf, final int offset) {
        return buf.get(offset + 3) << 24 | (buf.get(offset + 2) & 0xFF) << 16 | (buf.get(offset + 1) & 0xFF) << 8 | (buf.get(offset) & 0xFF);
    }

    public static void putArrInt(final ByteBuffer buf, final int offset, final int value) {
        buf.put(offset + 3, (byte)(value >> 24));
        buf.put(offset + 2, (byte)(value >> 16));
        buf.put(offset + 1, (byte)(value >> 8));
        buf.put(offset, (byte)value);
    }
}
