package com.bdoemu.core.network;

import com.bdoemu.commons.network.Client;
import com.bdoemu.commons.network.ClientState;
import com.bdoemu.commons.network.Connection;
import com.bdoemu.commons.network.ICipher;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.commons.network.impl.crypt.BDOCrypt;
import com.bdoemu.core.network.sendable.SMSetFrameworkInformation;
import com.bdoemu.login.models.db.Account;

/**
 * @ClassName LoginClient
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 11:58
 * VERSION 1.0
 */

public class LoginClient extends Client<LoginClient> {
    private byte[] mac;
    private Account account;
    private String pinTable;

    public LoginClient(final Connection<LoginClient> connection) {
        super(connection);
    }
    @Override
    protected void onOpen() {
        super.onOpen();
        final BDOCrypt cipher = new BDOCrypt();
        this.getConnection().setCipher(cipher);
        this.sendPacket((SendablePacket)new SMSetFrameworkInformation((ICipher)cipher));
    }
    @Override
    public String toString() {
        try {

            switch (this.getState()) {
                case CONNECTED: {
                    return super.toString();
                }
                case AUTHED_GG:
                case AUTHED:
                case ENTERED: {
                    return this.getHostAddress();
                }
                default: {
                    return super.toString();
                }
            }
        }
        catch (NullPointerException ex) {
            return super.toString();
        }
    }

    public byte[] getMac() {
        return this.mac;
    }

    public Account getAccount() {
        return this.account;
    }

    public String getPinTable() {
        return this.pinTable;
    }

    public void setMac(final byte[] mac) {
        this.mac = mac;
    }

    public void setAccount(final Account account) {
        this.account = account;
    }

    public void setPinTable(final String pinTable) {
        this.pinTable = pinTable;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LoginClient)) {
            return false;
        }
        final LoginClient loginClient = (LoginClient)o;
        if (!loginClient.canEqual(this)) {
            return false;
        }
        final Account account = this.getAccount();
        final Account account2 = loginClient.getAccount();
        Label_0083: {
            if (account == null) {
                if (account2 == null) {
                    break Label_0083;
                }
            }
            else if (account.equals(account2)) {
                break Label_0083;
            }
            return false;
        }
        final String pinTable = this.getPinTable();
        final String pinTable2 = loginClient.getPinTable();
        if (pinTable == null) {
            if (pinTable2 == null) {
                return true;
            }
        }
        else if (pinTable.equals(pinTable2)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object o) {
        return o instanceof LoginClient;
    }
    @Override
    public int hashCode() {
        final int n = 1 * 59 ;
        final Account account = this.getAccount();
        final int n2 = n * 59 + ((account == null) ? 43 : account.hashCode());
        final String pinTable = this.getPinTable();
        return n2 * 59 + ((pinTable == null) ? 43 : pinTable.hashCode());
    }
}
