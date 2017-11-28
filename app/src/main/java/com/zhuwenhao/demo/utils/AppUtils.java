package com.zhuwenhao.demo.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AppUtils {

    /**
     * 显示Toast
     *
     * @param context context
     * @param content content
     */
    public static void showToast(Context context, CharSequence content) {
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
        float b = size;
        DecimalFormat format = new DecimalFormat("0.00");

        //B
        if (b < 1024) {
            return format.format(b).replace(".00", "") + "B";
        } else {
            b = b / 1024;
        }

        //KB
        if (b < 1024) {
            return format.format(b).replace(".00", "") + "KB";
        } else {
            b = b / 1024;
        }

        //MB
        if (b < 1024) {
            return format.format(b).replace(".00", "") + "MB";
        } else {
            return format.format(b / 1024).replace(".00", "") + "GB";
        }
    }
}
