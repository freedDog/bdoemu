package com.bdoemu.core.xmlrpcservices;

import com.bdoemu.commons.model.xmlrpc.BaseXmlRpcHandler;
import com.bdoemu.commons.model.xmlrpc.XmlRpcMessageType;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcAccount;
import com.bdoemu.commons.xmlrpc.XmlRpcHandler;
import com.bdoemu.login.databaseCollections.AccountsDbCollection;
import com.bdoemu.login.models.db.Account;
import com.bdoemu.login.service.AuthTokenService;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @ClassName AccountXmlRpcHandler
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 18:22
 * VERSION 1.0
 */
@XmlRpcHandler
public class AccountXmlRpcHandler extends BaseXmlRpcHandler {

    /**
     * 注册
     * @param accountName
     * @param email
     * @param password
     * @param s4
     * @return
     */
    public String register(String accountName,  String email,  String password, final String s4) {
        final XmlRpcAccount xmlRpcAccount = new XmlRpcAccount(XmlRpcMessageType.ERROR);
        if (StringUtils.isBlank(accountName) || StringUtils.isBlank(password)) {
            xmlRpcAccount.setMessage("account is blank ");
            return this.json(xmlRpcAccount);
        }
        try {
            if (AccountsDbCollection.getInstance().exists(accountName,email)) {
                xmlRpcAccount.setMessage("");
                return this.json((Object)xmlRpcAccount);
            }
            if (AccountsDbCollection.getInstance().exists(accountName,email)) {
                xmlRpcAccount.setMessage("");
                return this.json((Object)xmlRpcAccount);
            }
            final Account account = new Account(accountName, email, password);
            // invokedynamic(\u35e3:(Lcom/bdoemu/login/models/db/Account;Ljava/lang/String;)V, account, s4)
            // invokedynamic(\ua386:(Lcom/bdoemu/login/databaseCollections/AccountsDbCollection;Lcom/bdoemu/commons/database/mongo/JSONable;)Z,
            // invokedynamic(\u873d:()Lcom/bdoemu/login/databaseCollections/AccountsDbCollection;), account)

            return this.json(account);
        }
        catch (Exception ex) {
            xmlRpcAccount.setMessage("register Error"+ex);
            return this.json((xmlRpcAccount));
        }
    }

    /**
     *
     * @param accountName
     * @param email
     * @param password
     * @param s4
     * @return
     */
    public String registerPlain(final String accountName, final String email, final String password, final String s4) {
        return this.register(accountName, email, BCrypt.hashpw(password, BCrypt.gensalt()), s4);
    }

    /**
     * 确认注册
     * @param s
     * @return
     */
    public String registerConfirmation(final String s) {
        final XmlRpcAccount xmlRpcAccount = new XmlRpcAccount(XmlRpcMessageType.ERROR);
        if (StringUtils.isBlank(s)) {
            xmlRpcAccount.setMessage("");
            return this.json(xmlRpcAccount);
        }
        final Account account = AccountsDbCollection.getInstance().loadOne("",s);
        if (account != null) {
            return this.json(account);
        }
        xmlRpcAccount.setMessage("");
        return this.json(xmlRpcAccount);
    }

    /**
     * 更改密码创建散列
     * @param s
     * @param s2
     * @return
     */
    public String changePasswordCreateHash(final String s, final String s2) {
        final XmlRpcAccount xmlRpcAccount = new XmlRpcAccount(XmlRpcMessageType.ERROR);
        if (StringUtils.isBlank(s) || StringUtils.isBlank(s2)) {
            xmlRpcAccount.setMessage("");
            return this.json(xmlRpcAccount);
        }
        final Account account = AccountsDbCollection.getInstance().loadOne("",s);
        if (account != null) {
            return this.json( account);
        }
        xmlRpcAccount.setMessage("");
        return this.json(xmlRpcAccount);
    }

    public String auth(final String accountName, final String password) {
        try {
            final Account account = AccountsDbCollection.getInstance().loadOne("",accountName);
            if (account == null) {
                return this.json(this.getMessageError(""));
            }
            if (!BCrypt.checkpw(password,account.getConfirmationHash())) {
                return this.json(this.getMessageError(""));
            }
            return this.getMessageOk();
        }
        catch (Exception ex) {
            return this.json(this.getMessageError(ex.getMessage()));
        }
    }

    public String changePassword(final String accountName, final String password) {
        final XmlRpcAccount xmlRpcAccount = new XmlRpcAccount(XmlRpcMessageType.ERROR);
        if (StringUtils.isBlank(accountName) || StringUtils.isBlank(password)) {
            xmlRpcAccount.setMessage("");
            return this.json(xmlRpcAccount);
        }
        final Account account = AccountsDbCollection.getInstance().loadOne("",accountName);
        if (account != null) {
            return this.json(account);
        }
        xmlRpcAccount.setMessage("");
        return this.json(xmlRpcAccount);
    }

    public String findByEmail(final String email) {
        final XmlRpcAccount xmlRpcAccount = new XmlRpcAccount(XmlRpcMessageType.ERROR);
        if (StringUtils.isBlank(email)) {
            xmlRpcAccount.setMessage("");
            return this.json(xmlRpcAccount);
        }
        final Account account = AccountsDbCollection.getInstance().loadOne("",email);
        if (account != null) {
            return this.json(account);
        }
        xmlRpcAccount.setMessage("");
        return this.json(xmlRpcAccount);
    }

    public String findByName(final String accountName) {
        final XmlRpcAccount xmlRpcAccount = new XmlRpcAccount(XmlRpcMessageType.ERROR);
        if (StringUtils.isBlank(accountName)) {
            xmlRpcAccount.setMessage("");
            return this.json(xmlRpcAccount);
        }
        final Account account = AccountsDbCollection.getInstance().loadOne("",accountName);
        if (account != null) {
            return this.json(account);
        }
        xmlRpcAccount.setMessage("");
        return this.json(xmlRpcAccount);
    }

    public String generateAuthToken(final String s) {
       return this.json(AuthTokenService.getInstance().getTokenData(s));
    }

    public String getCash(final Long n) {
        return this.json(AccountsDbCollection.getInstance().getCash(n));
    }
}
