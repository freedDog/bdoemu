package com.bdoemu.commons.network;

import com.bdoemu.core.configs.NetworkConfig;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @ClassName SendByteBuffer
 * @Description 发送byte buffer
 * @Author JiangBangMing
 * @Date 2019/6/22 13:49
 * VERSION 1.0
 */

public class SendByteBuffer {

    private ByteBuffer buffer;

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (!(o instanceof SendByteBuffer)){
            return false;
        }
        SendByteBuffer other = (SendByteBuffer)o;
        if (!other.canEqual(this)){
            return false;
        }
        Object this$buffer = getBuffer(), other$buffer = other.getBuffer();
        return !((this$buffer == null) ? (other$buffer != null) : !this$buffer.equals(other$buffer));
    }
    protected boolean canEqual(Object other) {
        return other instanceof SendByteBuffer;
    }
    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $buffer = getBuffer();
        return result * 59 + (($buffer == null) ? 43 : $buffer.hashCode());
    }
    @Override
    public String toString() {
        return "SendByteBuffer(buffer=" + getBuffer() + ")";
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public SendByteBuffer() {
        this.buffer = ByteBuffer.allocateDirect(NetworkConfig.SEND_BUFFER_SIZE);
        this.buffer.order(ByteOrder.LITTLE_ENDIAN);
    }


    public int position() {
        return this.buffer.position();
    }

    public Buffer position(int pos) {
        return this.buffer.position(pos);
    }

    public void limit(int limit) {
        this.buffer.limit(limit);
    }

    public int limit() {
        return this.buffer.limit();
    }

    public int remaining() {
        return this.buffer.remaining();
    }

    public boolean hasRemaining() {
        return this.buffer.hasRemaining();
    }

    public void flip() {
        this.buffer.flip();
    }

    public int capacity() {
        return this.buffer.capacity();
    }

    public void compact() {
        this.buffer.compact();
    }

    public void clear() {
        this.buffer.clear();
    }

    public final void writeC(boolean value) {
        this.buffer.put((byte)(value ? 1 : 0));
    }

    public final void writeC(int value) {
        this.buffer.put((byte)value);
    }

    public final void writeH(boolean value) {
        this.buffer.putShort((short)(value ? 1 : 0));
    }

    public final void writeH(int value) {
        this.buffer.putShort((short)value);
    }

    public final void writeD(boolean value) {
        this.buffer.putInt(value ? 1 : 0);
    }

    public final void writeD(int value) {
        this.buffer.putInt(value);
    }

    public final void writeD(long value) {
        this.buffer.putInt((int)(value & 0xFFFFFFFFFFFFFFFFL));
    }

    public final void writeQ(boolean value) {
        this.buffer.putLong(value ? 1L : 0L);
    }

    public final void writeQ(long value) {
        this.buffer.putLong(value);
    }

    public final void writeF(float value) {
        this.buffer.putFloat(value);
    }

    public final void writeF(double value) {
        this.buffer.putFloat((float)value);
    }

    public final void writeF(int value) {
        this.buffer.putFloat(value);
    }

    public final void writeB(byte[] data){
        this.buffer.put(data);
    }

    public final void writeB(int size) {
        this.buffer.put(new byte[size]);
    }

    public final void writeD3(int value) {
        this.buffer.put((byte)(value & 0xFF));
        this.buffer.put((byte)((value & 0xFF00) >> 8));
        this.buffer.put((byte)((value & 0xFF0000) >> 16));
    }


    public final void writeS(CharSequence... charSequences) {
    }

        public final void writess(CharSequence charSequence) {
            if (charSequence != null) {
                this.buffer.putShort((short)charSequence.length());
                int length = charSequence.length();
                for (int i = 0; i < length; i++) {
                    this.buffer.put((byte)(charSequence.charAt(i) & 0xFF));
                }
            } else {
                this.buffer.putShort((short)0);
            }
        }

        public final void writes(CharSequence charSequence, int size) {
            int length = 0;
            if (charSequence != null) {
                length = charSequence.length();
                for (int i = 0; i < length; i++) {
                    this.buffer.put((byte)(charSequence.charAt(i) & 0xFF));
                }
            }
            if (length < size) {
                writeB(new byte[size - length]);
            }
        }

        public final void writeSS(CharSequence charSequence) {
            this.buffer.putShort((short)(charSequence.length() * 2 + 2));
            putChars(charSequence);
            this.buffer.putChar((char) 0);
        }

        public final void writeS(CharSequence charSequence) {
            putChars(charSequence);
            this.buffer.putChar((char) 0);
        }
        public final void writeS(CharSequence charSequence, int size) {
            if (charSequence == null) {
                this.buffer.put(new byte[size]);
            } else {
                int length = charSequence.length();
                for (int i = 0; i < length; i++) {
                    this.buffer.putChar(charSequence.charAt(i));
                }

                this.buffer.putChar((char) 0);
                length = length * 2 + 2;
                if (length < size) {
                    writeB(new byte[size - length]);
                }
            }
        }

        private void putChars(CharSequence charSequence) {
            if (charSequence == null) {
                return;
            }

            int length = charSequence.length();
            for (int i = 0; i < length; i++) {
                this.buffer.putChar(charSequence.charAt(i));
            }
        }

        protected static byte[] hex2Byte(String str) {
            byte[] bytes = new byte[str.length() / 2];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte)Integer.parseInt(str.substring(2 * i, 2 * i + 2), 16);
            }
            return bytes;
        }
}
