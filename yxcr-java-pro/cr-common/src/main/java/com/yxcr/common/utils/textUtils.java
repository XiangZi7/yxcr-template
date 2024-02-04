package com.yxcr.common.utils;

public class textUtils {
    public static String getLastPartOfUrl(String url) {
        if (url == null || url.isEmpty()) {
            return ""; // 或者可以返回 null 或抛出一个异常
        }

        //删除末尾的 '/'
        url = url.endsWith("/") ? url.substring(0, url.length() - 1) : url;

        // 找到最后一个 '/' 的位置
        int lastIndex = url.lastIndexOf("/");

        // 提取最后一部分
        return lastIndex != -1 ? url.substring(lastIndex + 1) : url;
    }

}
