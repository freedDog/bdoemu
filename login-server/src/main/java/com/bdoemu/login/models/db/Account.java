package com.bdoemu.login.models.db;

import com.bdoemu.commons.database.mongo.JSONable;
import com.bdoemu.commons.model.enums.EAccessLevel;
import com.bdoemu.commons.model.xmlrpc.XMLRPCable;
import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcAccount;
import com.bdoemu.commons.rmi.model.Macros;
import com.bdoemu.login.idfactory.LSIDStorageType;
import com.bdoemu.login.idfactory.LoginServerIDFactory;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.util.Iterator;

/**
 * @ClassName Account
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:36
 * VERSION 1.0
 */

public class Account extends JSONable implements XMLRPCable {
    private final long objectId;
    private long cash;
    private String password;
    private String accountName;
    private String email;
    private String host;
    private EAccessLevel accessLvl;
    private String family;
    private String pin;
    private String confirmationHash;
    private String changePasswordHash;
    private long registrationDate;
    private int characterSlots;
    private Macros[] macroses;
    private String gameOption;
    private String uiInfo;

    public Account(final BasicDBObject basicDBObject) {
        this.accessLvl = EAccessLevel.USER;
        this.family = "";
        this.pin = "";
        this.confirmationHash = "";
        this.changePasswordHash = "";
        this.objectId = basicDBObject.getLong("_id");
        this.accountName = basicDBObject.getString("accountName");
        this.password = basicDBObject.getString("password");
        this.pin = basicDBObject.getString("pin");
        this.family = basicDBObject.getString("family");
        this.host = basicDBObject.getString("host");
//        this.accessLvl = EAccessLevel.valueOf(basicDBObject.getString("accessLvl"));
        this.characterSlots = basicDBObject.getInt("characterSlots");
        this.cash = basicDBObject.getInt("cash");
        if (basicDBObject.containsField("")) {
            final BasicDBList list = (BasicDBList)basicDBObject.get("");
            this.macroses = new Macros[10];
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                final Macros macros = new Macros((BasicDBObject)iterator.next());
                this.macroses[macros.getIndex()] = macros;
            }
        }
        if (basicDBObject.containsField("")) {
            this.email = basicDBObject.getString("");
        }
        if (basicDBObject.containsField("")) {
//            this.gameOption = ((BasicDBObject)basicDBObject.get("");
        }
        if (basicDBObject.containsField("")) {
//            this.uiInfo = ((BasicDBObject)basicDBObject.get(""));
        }
        if (basicDBObject.containsField("")) {
            this.confirmationHash = basicDBObject.getString("");
        }
        if (basicDBObject.containsField("")) {
            this.changePasswordHash = basicDBObject.getString("");
        }
        this.registrationDate = basicDBObject.getLong("", 0L);
    }

    public Account(final String accountName, final String email, final String password) {
        this.accessLvl = EAccessLevel.USER;
        this.family = "";
        this.pin = "";
        this.confirmationHash = "";
        this.changePasswordHash = "";
        this.objectId = LoginServerIDFactory.getInstance().nextId(LSIDStorageType.ACCOUNT);
        this.accountName = accountName;
        this.email = email;
        this.password = password;
        this.registrationDate = System.currentTimeMillis();
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder basicDBObjectBuilder = new BasicDBObjectBuilder();
        basicDBObjectBuilder.append("", this.objectId);
        basicDBObjectBuilder.append("", (Object)this.accountName);
        basicDBObjectBuilder.append("", (Object)this.email);
        basicDBObjectBuilder.append("", (Object)this.password);
        basicDBObjectBuilder.append("", (Object)this.pin);
        basicDBObjectBuilder.append("", (Object)this.family);
        basicDBObjectBuilder.append("", this.accessLvl.getAccessId());
        basicDBObjectBuilder.append("", this.characterSlots);
        basicDBObjectBuilder.append("", this.cash);
        basicDBObjectBuilder.append("", (Object)this.confirmationHash);
        basicDBObjectBuilder.append("", (Object)this.changePasswordHash);
        basicDBObjectBuilder.append("", this.registrationDate);
        return basicDBObjectBuilder.get();
    }

    @Override
    public XmlRpcMessage toXMLRpcObject(final String s) {
        final XmlRpcAccount xmlRpcAccount = new XmlRpcAccount();
        xmlRpcAccount.setAccountId(this.objectId);
        xmlRpcAccount.setAccountName(this.accountName);
        xmlRpcAccount.setEmail(this.email);
        xmlRpcAccount.setFamily(this.family);
        xmlRpcAccount.setCash(this.cash);
        xmlRpcAccount.setPassword(this.password);
        xmlRpcAccount.setAccessLevel(this.accessLvl);
        xmlRpcAccount.setConfirmationCode(this.confirmationHash);
        xmlRpcAccount.setChangePasswordCode(this.changePasswordHash);
        xmlRpcAccount.setRegistrationDate(this.registrationDate);
        return xmlRpcAccount;
    }

    @Override
    public long getObjectId() {
        return this.objectId;
    }

    public long getCash() {
        return this.cash;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getHost() {
        return this.host;
    }

    public EAccessLevel getAccessLvl() {
        return this.accessLvl;
    }

    public String getFamily() {
        return this.family;
    }

    public String getPin() {
        return this.pin;
    }

    public String getConfirmationHash() {
        return this.confirmationHash;
    }

    public String getChangePasswordHash() {
        return this.changePasswordHash;
    }

    public long getRegistrationDate() {
        return this.registrationDate;
    }

    public int getCharacterSlots() {
        return this.characterSlots;
    }

    public Macros[] getMacroses() {
        return this.macroses;
    }

    public String getGameOption() {
        return this.gameOption;
    }

    public String getUiInfo() {
        return this.uiInfo;
    }

    public void setCash(final long cash) {
        this.cash = cash;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setAccessLvl(final EAccessLevel accessLvl) {
        this.accessLvl = accessLvl;
    }

    public void setFamily(final String family) {
        this.family = family;
    }

    public void setPin(final String pin) {
        this.pin = pin;
    }

    public void setConfirmationHash(final String confirmationHash) {
        this.confirmationHash = confirmationHash;
    }

    public void setChangePasswordHash(final String changePasswordHash) {
        this.changePasswordHash = changePasswordHash;
    }

    public void setRegistrationDate(final long registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setCharacterSlots(final int characterSlots) {
        this.characterSlots = characterSlots;
    }

    public void setMacroses(final Macros[] macroses) {
        this.macroses = macroses;
    }

    public void setGameOption(final String gameOption) {
        this.gameOption = gameOption;
    }

    public void setUiInfo(final String uiInfo) {
        this.uiInfo = uiInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("ObjectId:"+this.getObjectId());
        return sb.toString();

    }
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        final Account account = (Account)o;
        if (!account.canEqual(this)) {
            return false;
        }
        if (this.getObjectId() != account.getObjectId()) {
            return false;
        }
        if (this.getCash() != account.getCash()) {
            return false;
        }
        final String password = this.getPassword();
        final String password2 = account.getPassword();
        Label_0093: {
            if (password == null) {
                if (password2 == null) {
                    break Label_0093;
                }
            }
            else if (password.equals(password2)) {
                break Label_0093;
            }
            return false;
        }
        if (this.getRegistrationDate() != account.getRegistrationDate()) {
            return false;
        }
        if (this.getCharacterSlots() != account.getCharacterSlots()) {
            return false;
        }
        final String uiInfo = this.getUiInfo();
        final String uiInfo2 = account.getUiInfo();
        if (uiInfo == null) {
            if (uiInfo2 == null) {
                return true;
            }
        }
        else if (uiInfo.equals(uiInfo2)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object o) {
        return o instanceof Account;
    }

    @Override
    public int hashCode() {
        final int n = 1;
        final long objectId = this.getObjectId();
        final int n2 = n * 59 + (int)(objectId >>> 32 ^ objectId);
        final long cash = this.getCash();
        final int n3 = n2 * 59 + (int)(cash >>> 32 ^ cash);
        final String password = this.getPassword();
        final int n4 = n3 * 59 + ((password == null) ? 43 : password.hashCode());
        final String accountName = this.getAccountName();
        final int n5 = n4 * 59 + ((accountName == null) ? 43 : accountName.hashCode());
        final String email = this.getEmail();
        final int n6 = n5 * 59 + ((email == null) ? 43 : email.hashCode());
        final String host = this.getHost();
        final int n7 = n6 * 59 + ((host == null) ? 43 : host.hashCode());
        final EAccessLevel accessLvl = this.getAccessLvl();
        final int n8 = n7 * 59 + ((accessLvl == null) ? 43 : accessLvl.hashCode());
        final String family = this.getFamily();
        final int n9 = n8 * 59 + ((family == null) ? 43 : family.hashCode());
        final String pin = this.getPin();
        final int n10 = n9 * 59 + ((pin == null) ? 43 : pin.hashCode());
        final String confirmationHash = this.getConfirmationHash();
        final int n11 = n10 * 59 + ((confirmationHash == null) ? 43 : confirmationHash.hashCode());
        final String changePasswordHash = this.getChangePasswordHash();
        final int n12 = n11 * 59 + ((changePasswordHash == null) ? 43 : changePasswordHash.hashCode());
        final long registrationDate = this.getRegistrationDate();
        final int n13 = ((n12 * 59 + (int)(registrationDate >>> 32 ^ registrationDate)) * 59 + this.getCharacterSlots()) * 59 +  this.getMacroses().length;
        final String gameOption = this.getGameOption();
        final int n14 = n13 * 59 + ((gameOption == null) ? 43 : gameOption.hashCode());
        final String uiInfo = this.getUiInfo();
        return n14 * 59 + ((uiInfo == null) ? 43 : uiInfo.hashCode());
    }
}
