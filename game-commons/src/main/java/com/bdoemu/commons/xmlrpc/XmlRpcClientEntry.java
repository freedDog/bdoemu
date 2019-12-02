package com.bdoemu.commons.xmlrpc;

/**
 * @ClassName XmlRpcClientEntry
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:49
 * VERSION 1.0
 */

public class XmlRpcClientEntry {

    private String name;
    private String host;
    private Integer port;
    private String login;
    private String password;

    public XmlRpcClientEntry(final String data) {
        final String[] datas = data.split(":");
        this.name = datas[0];
        this.host = datas[1];
        this.port = Integer.parseInt(datas[2]);
        if (datas.length > 3) {
            this.login = datas[3];
            this.password = datas[4];
        }
    }

    public String getName() {
        return this.name;
    }

    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcClientEntry)) {
            return false;
        }
        final XmlRpcClientEntry other = (XmlRpcClientEntry)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0065: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0065;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$host = this.getHost();
        final Object other$host = other.getHost();
        Label_0102: {
            if (this$host == null) {
                if (other$host == null) {
                    break Label_0102;
                }
            }
            else if (this$host.equals(other$host)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$port = this.getPort();
        final Object other$port = other.getPort();
        Label_0139: {
            if (this$port == null) {
                if (other$port == null) {
                    break Label_0139;
                }
            }
            else if (this$port.equals(other$port)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$login = this.getLogin();
        final Object other$login = other.getLogin();
        Label_0176: {
            if (this$login == null) {
                if (other$login == null) {
                    break Label_0176;
                }
            }
            else if (this$login.equals(other$login)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null) {
            if (other$password == null) {
                return true;
            }
        }
        else if (this$password.equals(other$password)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcClientEntry;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $host = this.getHost();
        result = result * 59 + (($host == null) ? 43 : $host.hashCode());
        final Object $port = this.getPort();
        result = result * 59 + (($port == null) ? 43 : $port.hashCode());
        final Object $login = this.getLogin();
        result = result * 59 + (($login == null) ? 43 : $login.hashCode());
        final Object $password = this.getPassword();
        result = result * 59 + (($password == null) ? 43 : $password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "XmlRpcClientEntry(name=" + this.getName() + ", host=" + this.getHost() + ", port=" + this.getPort() + ", login=" + this.getLogin() + ", password=" + this.getPassword() + ")";
    }
}
