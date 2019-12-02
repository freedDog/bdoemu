package com.bdoemu.commons.xmlrpc.common.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName LimitedInputStream
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:24
 * VERSION 1.0
 */

public class LimitedInputStream extends InputStream {

    private long available;
    private long markedAvailable;
    private InputStream in;

    public LimitedInputStream(InputStream pIn, int pAvailable) {
        this.in = pIn;
        this.available = pAvailable;
    }

    @Override
    public int read() throws IOException {
        if (this.available > 0L) {
            this.available--;
            return this.in.read();
        }
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.available > 0L) {
            if (len > this.available)
            {
                len = (int)this.available;
            }
            int read = this.in.read(b, off, len);
            if (read == -1) {
                this.available = 0L;
            } else {
                this.available -= read;
            }
            return read;
        }
        return -1;
    }

    @Override
    public long skip(long n) throws IOException {
        long skip = this.in.skip(n);
        if (this.available > 0L) {
            this.available -= skip;
        }
        return skip;
    }

    @Override
    public void mark(int readlimit) {
        this.in.mark(readlimit);
        this.markedAvailable = this.available;
    }

    @Override
    public void reset() throws IOException {
        this.in.reset();
        this.available = this.markedAvailable;
    }


    @Override
    public boolean markSupported() {
        return true;
    }
}
