package com.zhuwenhao.demo.utils;

import android.content.Context;
import android.widget.Toast;

public class AppUtils {

    /**
     * 显示Toast
     *
     * @param context context
     * @param content content
     */
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast
     *
     * @param context context
     * @param resId   resId
     */
    public static void showToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 字符串首字母大写
     *
     * @param str String
     * @return String
     */
    public static String firstLetterToUpper(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

    /**
     * 字节换算
     *
     * @param size size
     * @return B KB MB GB
     */
    public static String conversionByte(long size) {
        //B
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }

        //KB
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }

        //MB
        if (size < 1024) {
            size = size * 100;
            return String.valueOf(size / 100) + "." + String.valueOf(size % 100) + "MB";
        } else {
            size = size * 100 / 1024;
            return String.valueOf(size / 100) + "." + String.valueOf(size % 100) + "GB";
        }
    }
}
