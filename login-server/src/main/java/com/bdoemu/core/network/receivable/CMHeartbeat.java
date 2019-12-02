package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName CMHeartbeat
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:17
 * VERSION 1.0
 */

public class CMHeartbeat extends ReceivablePacket<LoginClient> {
    public CMHeartbeat(final short n) {
        super(n);
    }

    protected void read() {
    }

    public void runImpl() {
    }
}
