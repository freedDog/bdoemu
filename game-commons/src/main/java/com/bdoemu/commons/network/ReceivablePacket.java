package com.bdoemu.commons.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 * @ClassName ReceivablePacket
 * @Description 应收包
 * @Author JiangBangMing
 * @Date 2019/6/22 12:36
 * VERSION 1.0
 */

public abstract class ReceivablePacket<TClient extends Client<TClient>> implements Runnable,Cloneable {

    private static final Logger log = LoggerFactory.getLogger(ReceivablePacket.class); private TClient client;
    private ByteBuffer buffer;
    protected short opCode;
    public void setOpCode(short opCode) {
        this.opCode = opCode;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReceivablePacket)){
            return false;
        }
        ReceivablePacket<?> other = (ReceivablePacket)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$client = getClient(), other$client = other.getClient();
        if ((this$client == null) ? (other$client != null) : !this$client.equals(other$client)){
            return false;
        }
        Object this$buffer = getBuffer(), other$buffer = other.getBuffer();
        return ((this$buffer == null) ? (other$buffer != null) : !this$buffer.equals(other$buffer)) ? false : (!(getOpCode() != other.getOpCode()));
    }
    protected boolean canEqual(Object other) {
        return other instanceof ReceivablePacket;
    }
    @Override
    public int hashCode() {
        int PRIME = 59,result = 1;
        Object $client = getClient();
        result = result * 59 + (($client == null) ? 43 : $client.hashCode());
        Object $buffer = getBuffer(); result = result * 59 + (($buffer == null) ? 43 : $buffer.hashCode());
        return result * 59 + getOpCode();
    }
    public short getOpCode() {
        return this.opCode;
    }
    @Override
    public void run() {
        try {
            runImpl();
        }
        catch (Exception e) {
            log.error("Error while running {} packet", getClass().getSimpleName(), e);
        }
    }

    public ReceivablePacket(short opcode) {
        this.opCode = opcode;
    }

    public TClient getClient() {
        return this.client;
    }

    public void setClient(TClient client) {
        this.client = client;
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    protected int getAvailableBytes() {
        return getBuffer().remaining();
    }

    protected final void skip(int bytes) {
        if (this.buffer.remaining() < bytes) {
            throw new BufferUnderflowException();
        }

        this.buffer.position(this.buffer.position() + bytes);
    }

    protected final void skipAll() {
        this.buffer.position(this.buffer.limit());
    }

    protected final void readB(byte[] dst) {
        this.buffer.get(dst);
    }

    protected final byte[] readB(int len) {
        byte[] tmp = new byte[len];
        this.buffer.get(tmp);
        return tmp;
    }

    protected final void readB(byte[] dst, int offset, int len) {
        this.buffer.get(dst, offset, len);
    }

    protected final byte readC() {
        return (byte)(this.buffer.get() & 0xFF);
    }

    protected final int readCD() {
        return this.buffer.get() & 0xFF;
    }

    protected final boolean readCB() {
        return ((this.buffer.get() & 0xFF) == 1);
    }

    protected final int readHD() {
        return this.buffer.getShort() & 0xFFFF;
    }

    protected final short readH() {
        return (short)(this.buffer.getShort() & 0xFFFF);
    }

    protected final int readD() {
        return this.buffer.getInt();
    }

    protected final long readDQ() {
        return this.buffer.getInt() & 0xFFFFFFFFL;
    }

    protected final int readD3() {
       int value = this.buffer.get() & 0xFF;
        value |= this.buffer.get() << 8 & 0xFF00;
        return this.buffer.get() << 16 & 0xFF0000;
    }

    protected final long readQ() {
        return this.buffer.getLong();
    }
    protected final float readF() {
        return this.buffer.getFloat();
    }
    protected final String readS() {
        StringBuilder tb = new StringBuilder(); char c;
        while ((c = this.buffer.getChar()) != '\000') {
            tb.append(c);
        }

        return tb.toString();
    }

    protected final String readS(int padding) {
        StringBuilder tb = new StringBuilder();
        while (padding > 0) {
            padding -= 2;
            char c = this.buffer.getChar();
            if (c == '\000') {
                break;
            }
            tb.append(c);
        }
        skip(padding);
        return tb.toString();
    }

    protected final String readSS() {
        short size = this.buffer.getShort();
        StringBuilder tb = new StringBuilder();
        for (; size > 0; size = (short)(size - 2)) {
            char c; if ((c = this.buffer.getChar()) != '\000') {
                tb.append(c);
            }
        }

        return tb.toString();
    }

    protected final String reads(int size) {
        byte[] bytes = new byte[size];
        this.buffer.get(bytes);
        return new String(bytes);
    }

    protected final String readss() {
        short size = this.buffer.getShort();
        byte[] bytes = new byte[size];
        this.buffer.get(bytes);
        return new String(bytes);
    }


    protected void sendPacket(SendablePacket<TClient> packet) { this.client.getConnection().sendPacket(packet); }



    public ReceivablePacket<TClient> clonePacket() {
        try {
            return (ReceivablePacket)clone();
        } catch (Exception e) {
            log.error("Error while cloning ReceivablePacket: {}", getClass().getSimpleName(), e);
            return null;
        }
    }


    @Override
    public String toString() {
        return "{" + getClass().getSimpleName() + " from " + getClient() + "}";
    }

    protected abstract void read();

    public abstract void runImpl();
}
