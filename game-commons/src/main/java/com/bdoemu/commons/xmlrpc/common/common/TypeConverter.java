package com.bdoemu.commons.xmlrpc.common.common;

/**
 * @ClassName TypeConverter
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:17
 * VERSION 1.0
 */

public interface TypeConverter {

    boolean isConvertable(final Object p0);

    Object convert(final Object p0);

    Object backConvert(final Object p0);
}
