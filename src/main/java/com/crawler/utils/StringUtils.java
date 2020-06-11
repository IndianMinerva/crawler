package com.crawler.utils;

public final class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean beginsWith(String str, String prefix) {
        return isNotEmpty(str) && str.trim().startsWith(prefix);
    }
}
