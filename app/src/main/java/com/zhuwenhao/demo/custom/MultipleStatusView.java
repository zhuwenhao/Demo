package com.zhuwenhao.demo.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zhuwenhao.demo.R;

public class MultipleStatusView extends RelativeLayout {

    //状态 - 内容
    public static final int STATUS_CONTENT = 0;
    //状态 - 空
    public static final int STATUS_EMPTY = 1;
    //状态 - 错误
    public static final int STATUS_ERROR = 2;
    //状态 - 加载中
    public static final int STATUS_LOADING = 3;
    //状态 - 无网络
    public static final int STATUS_NO_NETWORK = 4;

    //当前状态
    private int currentStatus;

    //空视图ResourceId
    private int emptyViewResId;
    //错误视图ResourceId
    private int errorViewResId;
    //加载中视图ResourceId
    private int loadingViewResId;
    //无网络视图ResourceId
    private int noNetworkViewResId;

    //内容视图
    private View contentView;
    //空视图
    private View emptyView;
    //错误视图
    private View errorView;
    //加载中视图
    private View loadingView;
    //无网络视图
    private View noNetworkView;

    private LayoutInflater inflater;

    private OnClickListener onRetryClickListener;

    private ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    public MultipleStatusView(Context context) {
        this(context, null);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0);
        emptyViewResId = a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.layout_empty_view);
        errorViewResId = a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.layout_error_view);
        loadingViewResId = a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.layout_loading_view);
        noNetworkViewResId = a.getResourceId(R.styleable.MultipleStatusView_noNetworkView, R.layout.layout_no_network_view);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflater = LayoutInflater.from(getContext());
        showContent();
    }

    /**
     * 显示内容视图
     */
    public void showContent() {
        currentStatus = STATUS_CONTENT;
        if (contentView == null) {
            contentView = getChildAt(0);
        }
        switchView();
    }

    /**
     * 显示空视图
     */
    public void showEmpty() {
        currentStatus = STATUS_EMPTY;
        if (emptyView == null) {
            emptyView = inflater.inflate(emptyViewResId, null);
            if (onRetryClickListener != null) {
                emptyView.setOnClickListener(onRetryClickListener);
            }
            addView(emptyView, 0, layoutParams);
        }
        switchView();
    }

    /**
     * 显示错误视图
     */
    public void showError() {
        currentStatus = STATUS_ERROR;
        if (errorView == null) {
            errorView = inflater.inflate(errorViewResId, null);
            if (onRetryClickListener != null) {
                errorView.setOnClickListener(onRetryClickListener);
            }
            addView(errorView, 0, layoutParams);
        }
        switchView();
    }

    /**
     * 显示加载中视图
     */
    public void showLoading() {
        currentStatus = STATUS_LOADING;
        if (loadingView == null) {
            loadingView = inflater.inflate(loadingViewResId, null);
            addView(loadingView, 0, layoutParams);
        }
        switchView();
    }

    /**
     * 显示无网络视图
     */
    public void showNoNetwork() {
        currentStatus = STATUS_NO_NETWORK;
        if (noNetworkView == null) {
            noNetworkView = inflater.inflate(noNetworkViewResId, null);
            if (onRetryClickListener != null) {
                noNetworkView.setOnClickListener(onRetryClickListener);
            }
            addView(noNetworkView, 0, layoutParams);
        }
        switchView();
    }

    /**
     * 切换视图
     */
    private void switchView() {
        if (contentView != null)
            contentView.setVisibility(currentStatus == STATUS_CONTENT ? VISIBLE : GONE);
        if (emptyView != null)
            emptyView.setVisibility(currentStatus == STATUS_EMPTY ? VISIBLE : GONE);
        if (errorView != null)
            errorView.setVisibility(currentStatus == STATUS_ERROR ? VISIBLE : GONE);
        if (loadingView != null)
            loadingView.setVisibility(currentStatus == STATUS_LOADING ? VISIBLE : GONE);
        if (noNetworkView != null)
            noNetworkView.setVisibility(currentStatus == STATUS_NO_NETWORK ? VISIBLE : GONE);
    }

    /**
     * 设置重试事件
     *
     * @param onRetryClickListener onRetryClickListener
     */
    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    /**
     * 获取当前状态
     *
     * @return viewStatus
     */
    public int getCurrentStatus() {
        return currentStatus;
    }
}