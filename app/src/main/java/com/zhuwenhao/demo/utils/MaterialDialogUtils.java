package com.zhuwenhao.demo.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zhuwenhao.demo.R;

public class MaterialDialogUtils {

    /**
     * 弹出带滚动条的Dialog
     *
     * @param context    Context
     * @param horizontal 滚动条是否为水平方向
     * @return MaterialDialog
     */
    public static MaterialDialog showProgressDialog(Context context, boolean horizontal) {
        return new MaterialDialog.Builder(context)
                .content(R.string.please_wait)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .cancelable(false)
                .show();
    }

    /**
     * 弹出经典无标题的Dialog
     *
     * @param context    Context
     * @param contentRes 内容
     * @param callback   callback
     * @return MaterialDialog
     */
    public static MaterialDialog showBasicNoTitleDialog(Context context, int contentRes, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .content(contentRes)
                .positiveText(android.R.string.ok)
                .onPositive(callback)
                .negativeText(android.R.string.cancel)
                .show();
    }

    /**
     * 弹出经典无标题的Dialog
     *
     * @param context  Context
     * @param content  内容
     * @param callback callback
     * @return MaterialDialog
     */
    public static MaterialDialog showBasicNoTitleDialog(Context context, CharSequence content, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .content(content)
                .positiveText(android.R.string.ok)
                .onPositive(callback)
                .negativeText(android.R.string.cancel)
                .show();
    }

    /**
     * 弹出经典的Dialog
     *
     * @param context    Context
     * @param titleRes   标题
     * @param contentRes 内容
     * @param callback   callback
     * @return MaterialDialog
     */
    public static MaterialDialog showBasicDialog(Context context, int titleRes, int contentRes, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .title(titleRes)
                .content(contentRes)
                .positiveText(android.R.string.ok)
                .onPositive(callback)
                .negativeText(android.R.string.cancel)
                .show();
    }
}
