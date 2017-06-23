package com.zhuwenhao.demo.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
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

    public static MaterialDialog showLongListAndConfirmDialog(final Context context, int titleRes, final int contentRes, String[] items, final OnDialogDismissListener listener) {
        return new MaterialDialog.Builder(context)
                .title(titleRes)
                .items((CharSequence[]) items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(final MaterialDialog materialDialog, View itemView, final int position, final CharSequence text) {
                        showBasicNoTitleDialog(context, String.format(context.getResources().getString(contentRes), text), new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                materialDialog.dismiss();
                                listener.onDismiss(position, text);
                            }
                        });
                    }
                })
                .negativeText(android.R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .autoDismiss(false)
                .show();
    }

    public interface OnDialogDismissListener {
        void onDismiss(int position, CharSequence text);
    }
}
