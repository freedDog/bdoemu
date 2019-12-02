package com.bdoemu.commons.xmlrpc.common.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName ServerStreamConnection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:24
 * VERSION 1.0
 */

public interface ServerStreamConnection {

    InputStream newInputStream() throws IOException;

    OutputStream newOutputStream() throws IOException;

    void close() throws IOException;
}
