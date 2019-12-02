package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.model.enums.EBanCriteriaType;
import com.bdoemu.commons.model.enums.EBanType;
import com.bdoemu.commons.model.enums.ELogEntryType;
import com.bdoemu.commons.model.enums.EStringTable;
import com.bdoemu.commons.network.ClientState;
import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.configs.LoginConfig;
import com.bdoemu.core.configs.SecurityConfig;
import com.bdoemu.core.network.LoginClient;
import com.bdoemu.core.network.sendable.SMGetCreateUserInformationToAuthenticServer;
import com.bdoemu.core.network.sendable.SMGetWorldInformations;
import com.bdoemu.core.network.sendable.SMNak;
import com.bdoemu.login.databaseCollections.AccountsDbCollection;
import com.bdoemu.login.databaseCollections.LogsDbCollection;
import com.bdoemu.login.models.AuthTokenData;
import com.bdoemu.login.models.db.Account;
import com.bdoemu.login.models.db.Log;
import com.bdoemu.login.service.AuthTokenService;
import com.bdoemu.login.service.BanService;
import com.bdoemu.login.service.CookieService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName CMGetCreateUserInformationToAuthenticServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:18
 * VERSION 1.0
 */

public class CMGetCreateUserInformationToAuthenticServer extends ReceivablePacket<LoginClient> {
    private static final Logger log= LoggerFactory.getLogger(CMGetCreateUserInformationToAuthenticServer.class);
    private String data;
    private int clientVersion;
    private int cookie;
    private int unk;

    public CMGetCreateUserInformationToAuthenticServer(final short n) {
        super(n);
    }

    @Override
    protected void read() {
        this.data = this.readS(2050);
        this.cookie = this.readD();
        this.clientVersion = this.readD();
        this.unk = this.readD();
    }

    @Override
    public void runImpl() {
        final LoginClient loginClient = this.getClient();
        if (loginClient != null) {
            if (SecurityConfig.BAN_SYSTEM_ENABLE && BanService.getInstance().isBanned(0,EBanCriteriaType.IP,this.getClient().getHostAddress(),EBanType.IP)) {
                loginClient.sendPacket(new SMNak(EStringTable.eErrNoLoginFaildTypeBlocked,this.opCode));
                return;
            }
            if (this.cookie <= 0) {
                if (this.data.length()>0) {
                    final String[] array = this.data.split(",");
                    final String s = array[0].trim();
                    final String s2 = array[1].trim();
                    final Account account = AccountsDbCollection.getInstance().loadOne("accountName",s);
                    if (account != null) {
//                        if (BCrypt.checkpw(s2, account.getConfirmationHash())) {
                            this.tryLogin(loginClient, account, null);
                            return;
//                        }
                    }
                    else if (LoginConfig.AUTO_REGISTRATION) {
                        final Account account2 = new Account(s, s, BCrypt.hashpw(s2, BCrypt.gensalt()));
                        this.tryLogin(loginClient, account2, null);
                        return;
                    }
                }
                else if (LoginConfig.TOKEN_AUTH_ENABLED) {
                    final AuthTokenData authTokenData = AuthTokenService.getInstance().getTokenData(this.data);
                    if (authTokenData == null) {
                        this.getClient().sendPacket(new SMNak(EStringTable.eErrNoCryptogramTokenIsNull,this.opCode));
                        return;
                    }
                    final Account account3 = AccountsDbCollection.getInstance().loadOne("","");
                    if (account3 != null) {
                        this.tryLogin(loginClient, account3, authTokenData);
                        return;
                    }
                }
            } else {
                final long n = Long.parseLong(this.data);
                if (n > 0L) {
                    final Account account4 = AccountsDbCollection.getInstance().load(n,new Object[0]);
                    final Integer n2 = CookieService.getInstance().getCookie(n);
                    if (account4 != null && n2 != null && n2 == this.cookie) {
                        this.tryLogin(loginClient, account4, null);
                        return;
                    }
                }
            }
        }
        this.getClient().sendPacket(new SMNak(EStringTable.eErrNoUserNotExistOrNotEqualPassword));
    }

    private void tryLogin(final LoginClient loginClient, final Account account, final AuthTokenData authTokenData) {
        if (SecurityConfig.BAN_SYSTEM_ENABLE && BanService.getInstance().isBanned(0,EBanCriteriaType.ACCOUNT_ID,account.getAccountName(),EBanType.ACCOUNT)) {
            this.getClient().sendPacket(new SMNak(EStringTable.eErrNoLoginFaildTypeBlocked,this.opCode));
            return;
        }
        if (account.getAccessLvl().ordinal() < LoginConfig.CONNECT_ACCESS_LEVEL.ordinal()) {
            this.getClient().sendPacket(new SMNak(EStringTable.eErrNoAccountIsExpired,this.opCode));
            return;
        }
        if (loginClient.getState()== ClientState.CONNECTED||loginClient.getState()==ClientState.AUTHED_GG) {
            loginClient.compareAndSetState(ClientState.CONNECTED,ClientState.AUTHED_GG);
            loginClient.setAccount(account);
            this.getClient().sendPacket(new SMGetCreateUserInformationToAuthenticServer(loginClient));
            loginClient.sendPacket(new SMGetWorldInformations(account));
            this.logDatabase(account.getObjectId(),account.getAccountName());
            CMGetCreateUserInformationToAuthenticServer.log.info("");
        }
    }

    private void logDatabase(final long n, final String s) {
        LogsDbCollection.getInstance().save(new Log(n, ELogEntryType.LOGIN_GAME,s,null));
    }

    @Override
    public String toString() {
        return "";
    }
}
