package com.zthc.ewms.base.util;

import org.springframework.util.Assert;

/**
 * 断言工具类
 *
 * @author yyb
 * @create 2018-12-11-16:26
 */
public class AssertUtils extends Assert{

    /**
     * 断言不为null
     * @param str
     * @param message
     */
    public static void notEmpty(String str, String message) {
        if (str == null || String.valueOf(str).trim().equals("")) {
            throw new IllegalArgumentException(message);
        }
    }
}
