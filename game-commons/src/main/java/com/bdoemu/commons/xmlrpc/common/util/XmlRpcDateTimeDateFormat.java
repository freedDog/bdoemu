package com.bdoemu.commons.xmlrpc.common.util;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName XmlRpcDateTimeDateFormat
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:56
 * VERSION 1.0
 */

public abstract class XmlRpcDateTimeDateFormat extends XmlRpcDateTimeFormat{

    private static final long serialVersionUID = -5107387618606150784L;

    @Override
    public StringBuffer format(final Object pCalendar, final StringBuffer pBuffer, final FieldPosition pPos) {
        Object cal;
        if (pCalendar != null && pCalendar instanceof Date) {
            final Calendar calendar = Calendar.getInstance(this.getTimeZone());
            calendar.setTime((Date)pCalendar);
            cal = calendar;
        }
        else {
            cal = pCalendar;
        }
        return super.format(cal, pBuffer, pPos);
    }

    @Override
    public Object parseObject(final String pString, final ParsePosition pParsePosition) {
        final Calendar cal = (Calendar)super.parseObject(pString, pParsePosition);
        return (cal == null) ? null : cal.getTime();
    }
}
