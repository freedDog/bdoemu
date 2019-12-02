package com.bdoemu.commons.xmlrpc.common.common;

/**
 * @ClassName TypeConverterFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:18
 * VERSION 1.0
 */

public interface TypeConverterFactory {

    TypeConverter getTypeConverter(final Class p0);
}
