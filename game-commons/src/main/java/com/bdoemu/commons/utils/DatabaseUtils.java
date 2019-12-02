package com.bdoemu.commons.utils;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Set;

/**
 * @ClassName DatabaseUtils
 * @Description 数据库工具
 * @Author JiangBangMing
 * @Date 2019/6/22 17:11
 * VERSION 1.0
 */

public class DatabaseUtils {

    public static boolean hasColumn(ResultSetMetaData rsMeta, String columnName) throws SQLException {
        int columns = rsMeta.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsMeta.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }

    public static Object getFieldFromCursor(DBObject o, String fieldPath) {
        if (fieldPath != null) {
            String[] fieldParts = StringUtils.split(fieldPath, '.');
            int i = 1;
            Object val = o.get(fieldParts[0]);
            while (i < fieldParts.length && val instanceof DBObject) {
                String fieldEntryName = fieldParts[i];
                if (fieldEntryName.equals("*")) {
                    BasicDBList list = new BasicDBList();
                    Set<String> fieldNames = ((DBObject)val).keySet();
                    if (fieldNames.size() > 0) {
                        for (String dbFieldName : fieldNames) {
                            Object obj = ((DBObject)val).get(dbFieldName);
                            Object internalListObj = ((DBObject)obj).get(fieldParts[i + 1]);
                            if (internalListObj != null && internalListObj instanceof BasicDBList) {
                                list.addAll((BasicDBList)internalListObj);
                            }
                        }
                    }
                    return list;
                }

                val = ((DBObject)val).get(fieldParts[i]);
                i++;
            }

            return val;
        }
        return o;
    }
}
