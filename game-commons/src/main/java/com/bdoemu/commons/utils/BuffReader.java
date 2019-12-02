package com.bdoemu.commons.utils;

import java.nio.ByteBuffer;

/**
 * @ClassName BuffReader
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 17:06
 * VERSION 1.0
 */

public class BuffReader {

    protected final ByteBuffer buff;

    public static BuffReader init(final ByteBuffer buff) {
        return new BuffReader(buff);
    }

    public static BuffReader init(final byte[] array) {
        return new BuffReader(array);
    }

    public BuffReader(final ByteBuffer buff) {
        this.buff = buff;
    }

    public BuffReader(final byte[] array) {
        this.buff = ByteBuffer.wrap(array);
    }

    public final byte readC() {
        return (byte)(this.buff.get() & 0xFF);
    }

    public final boolean readCB() {
        return (this.buff.get() & 0xFF) == 0x1;
    }

    public final float readF() {
        return this.buff.getFloat();
    }

    public final int readD() {
        return this.buff.getInt();
    }

    public final long readQ() {
        return this.buff.getLong();
    }

    public final short readH() {
        return (short)(this.buff.getShort() & 0xFFFF);
    }

    public final int readHD() {
        return this.buff.getShort() & 0xFFFF;
    }

    public final byte[] readB(final int len) {
        final byte[] tmp = new byte[len];
        this.buff.get(tmp);
        return tmp;
    }

    public final int readCD() {
        return this.buff.get() & 0xFF;
    }

    public final void skipAll() {
        this.buff.position(this.buff.limit());
    }
}
