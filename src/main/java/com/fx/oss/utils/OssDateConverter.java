package com.fx.oss.utils;

import java.text.ParseException;
import org.apache.commons.beanutils.Converter;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

/**
 * 日期转换类。
 * <p>
 * 用于bean转换。
 * 
 * @author Weiyong Huang
 */
public class OssDateConverter implements Converter {

    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            return value;
        }

        if (value instanceof Long) {
            Long longValue = (Long) value;
            return new Date(longValue.longValue());
        }
        if (value instanceof String) {
            Date endTime = null;
            try {
                endTime = DateUtils.parseDate(value.toString(), new String[]{
                            "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss",
                            "yyyy-MM-dd HH:mm", "yyyy-MM-dd"});
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return endTime;
        }

        return null;
    }
}
