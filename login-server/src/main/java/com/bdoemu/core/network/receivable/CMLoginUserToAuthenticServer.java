package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.model.enums.EGameServiceType;
import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.configs.LoginConfig;
import com.bdoemu.core.network.LoginClient;
import com.bdoemu.core.network.sendable.SMLoginUserToAuthenticServer;
import com.bdoemu.login.service.LoginServerRMI;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName CMLoginUserToAuthenticServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:20
 * VERSION 1.0
 */

public class CMLoginUserToAuthenticServer extends ReceivablePacket<LoginClient> {

    private static final Logger log= LoggerFactory.getLogger(CMLoginUserToAuthenticServer.class);
    private int index;
    private int zeroNumbers;
    private int cookie;
    private int screenWidth;
    private int screenHeight;
    private String gpu;
    private String cpu;
    private String pcData;
    private String osData;
    private long pin;

    public CMLoginUserToAuthenticServer(final short n) {
        super(n);
    }

    @Override
    protected void read() {
        this.skip(62);
        this.index = this.readC();
        this.zeroNumbers = this.readC();
        final int n = this.index * 8;
        this.skip(n);
        this.pin = this.readQ();
        this.skip(160 - (n + 8));
        this.cookie = this.readD();
        this.cpu = this.reads(50);
        this.readC();
        this.readD();
        this.gpu = this.reads(50);
        this.readC();
        this.screenWidth = this.readD();
        this.screenHeight = this.readD();
        this.readC();
        this.readC();
        this.pcData = this.reads(200);
        this.readC();
        this.osData = this.reads(200);
        this.readC();
        this.readC();
    }

    @Override
    public void runImpl() {
        final LoginClient loginClient = this.getClient();
        if (loginClient != null) {
            if (this.cookie == 0) {
                final char[] array = loginClient.getAccount().getAccountName().toCharArray();
                final char[] array2 =String.valueOf(this.pin).toCharArray();
                final StringBuilder sb = new StringBuilder(StringUtils.repeat(array[0], this.zeroNumbers));
                final char[] array3 = array2;
                for (int length = array3.length, i = 0; i < length; ++i) {
                    sb.append(array3[i]);
                }
                if (this.getClient().getAccount().getObjectId()>0) {

                }
                if (LoginConfig.GAME_SERVICE_TYPE == EGameServiceType.NA_REAL ) {
                }
                else {
                }
                loginClient.sendPacket(new SMLoginUserToAuthenticServer(this.getClient().getAccount(),this.cookie));
            }else{
                loginClient.sendPacket(new SMLoginUserToAuthenticServer(this.getClient().getAccount(),this.cookie));
            }
        }
    }

}
