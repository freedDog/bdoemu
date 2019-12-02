package com.bdoemu.commons.xmlrpc.common.util;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @ClassName XmlRpcDateTimeFormat
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:55
 * VERSION 1.0
 */

public abstract class XmlRpcDateTimeFormat extends Format {

    private static final long serialVersionUID = -8008230377361175138L;

    protected abstract TimeZone getTimeZone();

    private int parseInt(final String pString, int pOffset, final StringBuffer pDigits, int pMaxDigits) {
        final int length = pString.length();
        pDigits.setLength(0);
        while (pMaxDigits-- > 0 && pOffset < length) {
            final char c = pString.charAt(pOffset);
            if (!Character.isDigit(c)) {
                break;
            }
            pDigits.append(c);
            ++pOffset;
        }
        return pOffset;
    }

    @Override
    public Object parseObject(final String pString, final ParsePosition pParsePosition) {
        if (pString == null) {
            throw new NullPointerException("The String argument must not be null.");
        }
        if (pParsePosition == null) {
            throw new NullPointerException("The ParsePosition argument must not be null.");
        }
        int offset = pParsePosition.getIndex();
        final int length = pString.length();
        final StringBuffer digits = new StringBuffer();
        offset = this.parseInt(pString, offset, digits, 4);
        if (digits.length() < 4) {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        final int year = Integer.parseInt(digits.toString());
        offset = this.parseInt(pString, offset, digits, 2);
        if (digits.length() != 2) {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        final int month = Integer.parseInt(digits.toString());
        offset = this.parseInt(pString, offset, digits, 2);
        if (digits.length() != 2) {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        final int mday = Integer.parseInt(digits.toString());
        if (offset >= length || pString.charAt(offset) != 'T') {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        ++offset;
        offset = this.parseInt(pString, offset, digits, 2);
        if (digits.length() != 2) {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        final int hour = Integer.parseInt(digits.toString());
        if (offset >= length || pString.charAt(offset) != ':') {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        ++offset;
        offset = this.parseInt(pString, offset, digits, 2);
        if (digits.length() != 2) {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        final int minute = Integer.parseInt(digits.toString());
        if (offset >= length || pString.charAt(offset) != ':') {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        ++offset;
        offset = this.parseInt(pString, offset, digits, 2);
        if (digits.length() != 2) {
            pParsePosition.setErrorIndex(offset);
            return null;
        }
        final int second = Integer.parseInt(digits.toString());
        final Calendar cal = Calendar.getInstance(this.getTimeZone());
        cal.set(year, month - 1, mday, hour, minute, second);
        cal.set(14, 0);
        pParsePosition.setIndex(offset);
        return cal;
    }

    private void append(final StringBuffer pBuffer, final int pNum, final int pMinLen) {
        final String s = Integer.toString(pNum);
        for (int i = s.length(); i < pMinLen; ++i) {
            pBuffer.append('0');
        }
        pBuffer.append(s);
    }

    @Override
    public StringBuffer format(final Object pCalendar, final StringBuffer pBuffer, final FieldPosition pPos) {
        if (pCalendar == null) {
            throw new NullPointerException("The Calendar argument must not be null.");
        }
        if (pBuffer == null) {
            throw new NullPointerException("The StringBuffer argument must not be null.");
        }
        if (pPos == null) {
            throw new NullPointerException("The FieldPosition argument must not be null.");
        }
        final Calendar cal = (Calendar)pCalendar;
        final int year = cal.get(1);
        this.append(pBuffer, year, 4);
        this.append(pBuffer, cal.get(2) + 1, 2);
        this.append(pBuffer, cal.get(5), 2);
        pBuffer.append('T');
        this.append(pBuffer, cal.get(11), 2);
        pBuffer.append(':');
        this.append(pBuffer, cal.get(12), 2);
        pBuffer.append(':');
        this.append(pBuffer, cal.get(13), 2);
        return pBuffer;
    }
}
