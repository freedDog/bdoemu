package com.bdoemu.commons.xmlrpc.common.parser;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.serializer.ByteArraySerializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @ClassName SerializableParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:44
 * VERSION 1.0
 */

public class SerializableParser extends ByteArrayParser {

    @Override
    public Object getResult() throws XmlRpcException {
        try {
            final byte[] res = (byte[])super.getResult();
            final ByteArrayInputStream bais = new ByteArrayInputStream(res);
            final ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (IOException e) {
            throw new XmlRpcException("Failed to read result object: " + e.getMessage(), e);
        }
        catch (ClassNotFoundException e2) {
            throw new XmlRpcException("Failed to load class for result object: " + e2.getMessage(), e2);
        }
    }
}
