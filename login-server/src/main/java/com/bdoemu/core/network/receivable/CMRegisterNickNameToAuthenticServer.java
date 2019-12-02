package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.LoginClient;

/**
 * @ClassName CMRegisterNickNameToAuthenticServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/28 14:23
 * VERSION 1.0
 */

public class CMRegisterNickNameToAuthenticServer extends ReceivablePacket<LoginClient> {

    private String familyName;

    public CMRegisterNickNameToAuthenticServer(final short n) {
        super(n);
    }

    @Override
    protected void read() {
        this.familyName = this.readS(62);
        this.readD();
        this.readD();
    }

    @Override
    public void runImpl() {
    }
}
