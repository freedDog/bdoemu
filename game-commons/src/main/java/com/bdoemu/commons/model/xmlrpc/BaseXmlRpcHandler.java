package com.bdoemu.commons.model.xmlrpc;

import com.google.gson.Gson;

/**
 * @ClassName BaseXmlRpcHandler
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 19:54
 * VERSION 1.0
 */

public class BaseXmlRpcHandler {
    protected final Gson jsonObject;
    private final XmlRpcMessage messageOk;
    private final XmlRpcMessage messageError;

    public BaseXmlRpcHandler() {
        this.jsonObject = new Gson();
        (this.messageOk = new XmlRpcMessage()).setType(XmlRpcMessageType.OK);
        (this.messageError = new XmlRpcMessage()).setType(XmlRpcMessageType.ERROR);
    }

    protected String getMessageOk() {
        this.messageOk.setMessage(null);
        return this.json(this.messageOk);
    }

    protected String getMessageOk(final String message) {
        this.messageOk.setMessage(message);
        return this.json(this.messageOk);
    }

    protected String getMessageError() {
        this.messageError.setMessage(null);
        return this.json(this.messageError);
    }

    protected String getMessageError(final String message) {
        this.messageError.setMessage(message);
        return this.json(this.messageError);
    }

    public <T> String json(final T data) {
        return this.jsonObject.toJson((Object)data);
    }

    public <T> T unJson(final String data, final Class<T> objectType) {
        return (T)this.jsonObject.fromJson(data, (Class)objectType);
    }
}
