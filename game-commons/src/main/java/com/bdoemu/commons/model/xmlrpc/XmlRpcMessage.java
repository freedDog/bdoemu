package com.bdoemu.commons.model.xmlrpc;

/**
 * @ClassName XmlRpcMessage
 * @Description XML RPC  消息
 * @Author JiangBangMing
 * @Date 2019/6/24 19:57
 * VERSION 1.0
 */

public class XmlRpcMessage {
    private XmlRpcMessageType type;
    private String message;

    public XmlRpcMessage() {
        this.type = XmlRpcMessageType.OK;
    }

    public XmlRpcMessage(final XmlRpcMessageType type) {
        this.type = XmlRpcMessageType.OK;
        this.type = type;
    }

    public XmlRpcMessage(final XmlRpcMessageType type, final String message) {
        this.type = XmlRpcMessageType.OK;
        this.type = type;
        this.message = message;
    }

    public XmlRpcMessageType getType() {
        return this.type;
    }

    public String getMessage() {
        return this.message;
    }

    public void setType(final XmlRpcMessageType type) {
        this.type = type;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcMessage)) {
            return false;
        }
        final XmlRpcMessage other = (XmlRpcMessage)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0065: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0065;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null) {
            if (other$message == null) {
                return true;
            }
        }
        else if (this$message.equals(other$message)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcMessage;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $message = this.getMessage();
        result = result * 59 + (($message == null) ? 43 : $message.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "XmlRpcMessage(type=" + this.getType() + ", message=" + this.getMessage() + ")";
    }
}
