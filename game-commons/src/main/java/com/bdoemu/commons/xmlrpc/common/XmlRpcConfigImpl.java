package com.bdoemu.commons.xmlrpc.common;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcHttpConfig;

import java.util.TimeZone;

/**
 * @ClassName XmlRpcConfigImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:51
 * VERSION 1.0
 */

public abstract class XmlRpcConfigImpl implements XmlRpcConfig, XmlRpcHttpConfig {

    private boolean enabledForExtensions;
    private boolean contentLengthOptional;
    private String basicEncoding;
    private String encoding;
    private TimeZone timeZone;

    public XmlRpcConfigImpl() {
        this.timeZone = TimeZone.getDefault();
    }

    @Override
    public boolean isEnabledForExtensions() {
        return this.enabledForExtensions;
    }

    public void setEnabledForExtensions(final boolean pExtensions) {
        this.enabledForExtensions = pExtensions;
    }

    public void setBasicEncoding(final String pEncoding) {
        this.basicEncoding = pEncoding;
    }

    @Override
    public String getBasicEncoding() {
        return this.basicEncoding;
    }

    public void setEncoding(final String pEncoding) {
        this.encoding = pEncoding;
    }

    @Override
    public String getEncoding() {
        return this.encoding;
    }

    @Override
    public boolean isContentLengthOptional() {
        return this.contentLengthOptional;
    }

    public void setContentLengthOptional(final boolean pContentLengthOptional) {
        this.contentLengthOptional = pContentLengthOptional;
    }

    @Override
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(final TimeZone pTimeZone) {
        this.timeZone = pTimeZone;
    }
}
