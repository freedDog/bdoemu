package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.enums.EAccessLevel;
import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;
import com.bdoemu.commons.model.xmlrpc.XmlRpcMessageType;

/**
 * @ClassName XmlRpcAccount
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/27 11:25
 * VERSION 1.0
 */

public class XmlRpcAccount extends XmlRpcMessage {
    private long accountId;
    private String accountName;
    private String email;
    private String password;
    private String family;
    private long cash;
    private EAccessLevel accessLevel;
    private String confirmationCode;
    private String changePasswordCode;
    private long registrationDate;

    public XmlRpcAccount() {
    }

    public XmlRpcAccount(final XmlRpcMessageType type) {
        super(type);
    }

    public long getAccountId() {
        return this.accountId;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFamily() {
        return this.family;
    }

    public long getCash() {
        return this.cash;
    }

    public EAccessLevel getAccessLevel() {
        return this.accessLevel;
    }

    public String getConfirmationCode() {
        return this.confirmationCode;
    }

    public String getChangePasswordCode() {
        return this.changePasswordCode;
    }

    public long getRegistrationDate() {
        return this.registrationDate;
    }

    public void setAccountId(final long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setFamily(final String family) {
        this.family = family;
    }

    public void setCash(final long cash) {
        this.cash = cash;
    }

    public void setAccessLevel(final EAccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setConfirmationCode(final String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public void setChangePasswordCode(final String changePasswordCode) {
        this.changePasswordCode = changePasswordCode;
    }

    public void setRegistrationDate(final long registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "XmlRpcAccount(accountId=" + this.getAccountId() + ", accountName=" + this.getAccountName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", family=" + this.getFamily() + ", cash=" + this.getCash() + ", accessLevel=" + this.getAccessLevel() + ", confirmationCode=" + this.getConfirmationCode() + ", changePasswordCode=" + this.getChangePasswordCode() + ", registrationDate=" + this.getRegistrationDate() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcAccount)) {
            return false;
        }
        final XmlRpcAccount other = (XmlRpcAccount)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getAccountId() != other.getAccountId()) {
            return false;
        }
        final Object this$accountName = this.getAccountName();
        final Object other$accountName = other.getAccountName();
        Label_0079: {
            if (this$accountName == null) {
                if (other$accountName == null) {
                    break Label_0079;
                }
            }
            else if (this$accountName.equals(other$accountName)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        Label_0116: {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0116;
                }
            }
            else if (this$email.equals(other$email)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        Label_0153: {
            if (this$password == null) {
                if (other$password == null) {
                    break Label_0153;
                }
            }
            else if (this$password.equals(other$password)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$family = this.getFamily();
        final Object other$family = other.getFamily();
        Label_0190: {
            if (this$family == null) {
                if (other$family == null) {
                    break Label_0190;
                }
            }
            else if (this$family.equals(other$family)) {
                break Label_0190;
            }
            return false;
        }
        if (this.getCash() != other.getCash()) {
            return false;
        }
        final Object this$accessLevel = this.getAccessLevel();
        final Object other$accessLevel = other.getAccessLevel();
        Label_0241: {
            if (this$accessLevel == null) {
                if (other$accessLevel == null) {
                    break Label_0241;
                }
            }
            else if (this$accessLevel.equals(other$accessLevel)) {
                break Label_0241;
            }
            return false;
        }
        final Object this$confirmationCode = this.getConfirmationCode();
        final Object other$confirmationCode = other.getConfirmationCode();
        Label_0278: {
            if (this$confirmationCode == null) {
                if (other$confirmationCode == null) {
                    break Label_0278;
                }
            }
            else if (this$confirmationCode.equals(other$confirmationCode)) {
                break Label_0278;
            }
            return false;
        }
        final Object this$changePasswordCode = this.getChangePasswordCode();
        final Object other$changePasswordCode = other.getChangePasswordCode();
        if (this$changePasswordCode == null) {
            if (other$changePasswordCode == null) {
                return this.getRegistrationDate() == other.getRegistrationDate();
            }
        }
        else if (this$changePasswordCode.equals(other$changePasswordCode)) {
            return this.getRegistrationDate() == other.getRegistrationDate();
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcAccount;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $accountId = this.getAccountId();
        result = result * 59 + (int)($accountId >>> 32 ^ $accountId);
        final Object $accountName = this.getAccountName();
        result = result * 59 + (($accountName == null) ? 43 : $accountName.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * 59 + (($password == null) ? 43 : $password.hashCode());
        final Object $family = this.getFamily();
        result = result * 59 + (($family == null) ? 43 : $family.hashCode());
        final long $cash = this.getCash();
        result = result * 59 + (int)($cash >>> 32 ^ $cash);
        final Object $accessLevel = this.getAccessLevel();
        result = result * 59 + (($accessLevel == null) ? 43 : $accessLevel.hashCode());
        final Object $confirmationCode = this.getConfirmationCode();
        result = result * 59 + (($confirmationCode == null) ? 43 : $confirmationCode.hashCode());
        final Object $changePasswordCode = this.getChangePasswordCode();
        result = result * 59 + (($changePasswordCode == null) ? 43 : $changePasswordCode.hashCode());
        final long $registrationDate = this.getRegistrationDate();
        result = result * 59 + (int)($registrationDate >>> 32 ^ $registrationDate);
        return result;
    }
}
