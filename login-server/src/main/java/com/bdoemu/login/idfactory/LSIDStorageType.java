package com.bdoemu.login.idfactory;

/**
 * @ClassName LSIDStorageType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:31
 * VERSION 1.0
 */
public enum LSIDStorageType {
    /***/
    ACCOUNT(0L, Long.MAX_VALUE),
    PAYMENT(0L, Long.MAX_VALUE),
    BAN(0L, Long.MAX_VALUE),
    LOG(0L, Long.MAX_VALUE);

    private long minId;
    private long maxId;

    private LSIDStorageType(final long minId, final long maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }

    public long getMinId() {
        return this.minId;
    }

    public long getMaxId() {
        return this.maxId;
    }
}
