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
}
