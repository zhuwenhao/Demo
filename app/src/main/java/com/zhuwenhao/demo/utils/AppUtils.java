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
}
