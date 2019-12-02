package com.bdoemu.commons.xmlrpc.common;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @ClassName XmlRpcException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 12:03
 * VERSION 1.0
 */

public class XmlRpcException extends Exception{
    private static final long serialVersionUID = 3258693217049325618L;
    public final int code;
    public final Throwable linkedException;

    public XmlRpcException(int pCode, String pMessage) {
        this(pCode, pMessage, null);
    }

    public XmlRpcException(String pMessage, Throwable pLinkedException) {
        this(0, pMessage, pLinkedException);
    }

    public XmlRpcException(String pMessage) {
        this(0, pMessage, null);
    }

    public XmlRpcException(int pCode, String pMessage, Throwable pLinkedException) {
        super(pMessage);
        this.code = pCode;
        this.linkedException = pLinkedException;
    }
    @Override
    public void printStackTrace(PrintStream pStream) {
        super.printStackTrace(pStream);
        if (this.linkedException != null) {
            pStream.println("Caused by:");
            this.linkedException.printStackTrace(pStream);
        }
    }

    @Override
    public void printStackTrace(PrintWriter pWriter) {
        super.printStackTrace(pWriter);
        if (this.linkedException != null) {
            pWriter.println("Caused by:");
            this.linkedException.printStackTrace(pWriter);
        }
    }
    @Override
    public Throwable getCause() {
        return this.linkedException;
    }
}
