package com.bdoemu.commons.io;

import com.sun.nio.zipfs.ZipPath;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName FileBinaryReader
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 17:39
 * VERSION 1.0
 */

public class FileBinaryReader implements AutoCloseable {

    private static final Logger log= LoggerFactory.getLogger(FileBinaryReader.class);;
    private FileChannel roChannel;
    private ByteBuffer byteBuffer;

    public FileBinaryReader(final String path) {
        this(Paths.get(path, new String[0]));
    }

    public FileBinaryReader(final Path path) {
        this.roChannel = null;
        if (path instanceof ZipPath) {
            try (final InputStream is = Files.newInputStream(path, new OpenOption[0]);
                 final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                IOUtils.copy(is,output);
                (this.byteBuffer = ByteBuffer.wrap(output.toByteArray())).order(ByteOrder.LITTLE_ENDIAN);
            }
            catch (Exception e) {
                FileBinaryReader.log.error("Error while opening reader for file " + path.getFileName(), (Throwable)e);
            }
        }
        else {
            try {
                this.roChannel = new RandomAccessFile(path.toFile(), "r").getChannel();
                final int size = (int)this.roChannel.size();
                (this.byteBuffer = this.roChannel.map(FileChannel.MapMode.READ_ONLY, 0L, size).load()).order(ByteOrder.LITTLE_ENDIAN);
            }
            catch (Exception ex) {
                FileBinaryReader.log.error("Error while opening reader for file " + path.getFileName(), (Throwable)ex);
            }
        }
    }

    public boolean hasRemaining() {
        return this.byteBuffer.hasRemaining();
    }

    public byte[] readB(final int size) {
        final byte[] temp = new byte[size];
        this.readB(temp);
        return temp;
    }

    public void readB(final byte[] dst) {
        this.byteBuffer.get(dst);
    }

    public void readB(final byte[] dst, final int offset, final int len) {
        this.byteBuffer.get(dst, offset, len);
    }

    public int readCD() {
        return this.byteBuffer.get() & 0xFF;
    }

    public int readC() {
        return this.byteBuffer.get();
    }

    public boolean readCB() {
        return (this.byteBuffer.get() & 0xFF) == 0x1;
    }

    public short readH() {
        return (short)(this.byteBuffer.getShort() & 0xFFFF);
    }

    public int readHD() {
        return this.byteBuffer.getShort() & 0xFFFF;
    }

    public int readD() {
        return this.byteBuffer.getInt();
    }

    public long readDQ() {
        return (long)this.byteBuffer.getInt() & 0xFFFFFFFFL;
    }

    public long readQ() {
        return this.byteBuffer.getLong();
    }

    public int readQD() {
        return (int)this.byteBuffer.getLong();
    }

    public float readF() {
        return this.byteBuffer.getFloat();
    }

    public double readFF() {
        return this.byteBuffer.getDouble();
    }

    public String readS() {
        final StringBuilder sb = new StringBuilder();
        char ch;
        while ((ch = this.byteBuffer.getChar()) != '\0') {
            sb.append(ch);
        }
        return sb.toString();
    }

    public String readQS() {
        return this.readS(this.readQD());
    }

    public String readS(final int size, final Charset charset) {
        final byte[] strBytes = new byte[size];
        this.readB(strBytes);
        return new String(strBytes, charset);
    }

    public String readS(final int size) {
        return this.readS(size, Charset.defaultCharset());
    }

    public String readS(final long size) {
        return this.readS((int)size, Charset.defaultCharset());
    }

    public void setPosition(final int position) {
        this.byteBuffer.position(position);
    }

    public int getPosition() {
        return this.byteBuffer.position();
    }

    public String getPositionHex() {
        return Integer.toHexString(this.getPosition());
    }

    public int getAvailableBytes() {
        return this.byteBuffer.remaining();
    }

    public byte[] toByteArray() {
        return this.byteBuffer.array();
    }

    public final void skip(final int bytes) {
        if (this.byteBuffer.remaining() < bytes) {
            throw new BufferUnderflowException();
        }
        this.byteBuffer.position(this.byteBuffer.position() + bytes);
    }

    @Override
    public void close() {
        if (this.roChannel != null) {
            try {
                this.roChannel.close();
            }
            catch (IOException e) {
                FileBinaryReader.log.error("Error while closing FileBinaryReader channel!", (Throwable)e);
            }
        }
        if (this.byteBuffer != null) {
            try {
                this.byteBuffer.clear();
            }
            catch (Exception e2) {
                FileBinaryReader.log.error("Error while closing FileBinaryReader buffer!", (Throwable)e2);
            }
        }
    }
}
