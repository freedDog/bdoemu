package com.bdoemu.commons.xmlrpc.client;

/**
 * @ClassName XmlRpcCacheEntry
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 11:57
 * VERSION 1.0
 */

public class XmlRpcCacheEntry {

    private Object result;
    private Long validUntilTimeStamp;

    public Object getResult() {
        return this.result;
    }

    public Long getValidUntilTimeStamp() {
        return this.validUntilTimeStamp;
    }

    public void setResult(final Object result) {
        this.result = result;
    }

    public void setValidUntilTimeStamp(final Long validUntilTimeStamp) {
        this.validUntilTimeStamp = validUntilTimeStamp;
    }
}
