package com.codelang.loadinglayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author wangqi
 * @since 2017/12/11 14:04
 */

public class LoadingLayout extends ConstraintLayout {
    /**
     * 正在加载的布局
     */
    private View loadingView;
    /**
     * 错误布局
     */
    private View errorView;
    /**
     * 空布局
     */
    private View emptyView;


    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化
     * 添加"空布局"、"错误布局"、"正在加载布局"
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        /*****  加载正在加载页面  *******/
        loadingView = inflater.inflate(R.layout.loading_load, this, false);
        addView(loadingView);
        /****  加载错误页面  *******/
        errorView = inflater.inflate(R.layout.loading_error, this, false);
        addView(errorView);
        /*****  加载空页面  *******/
        emptyView = inflater.inflate(R.layout.loading_empty, this, false);
        addView(emptyView);

        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

//        errorView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iLoadingListener.error();
//                showLoadingView();
//            }
//        });


    }

    /**
     * 显示主布局
     */
    public void showContentView() {
        removeView(errorView);
        removeView(emptyView);
        removeView(loadingView);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示证咋加载错误布局
     */
    public void showErrorView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        errorView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示正在加载布局
     */
    public void showLoadingView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        loadingView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示空布局
     */
    public void showEmptyView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        emptyView.setVisibility(View.VISIBLE);
    }

//    ILoadingListener iLoadingListener;
//
//    public void setLoadErrorListener(ILoadingListener iLoadingListener) {
//        this.iLoadingListener = iLoadingListener;
//    }

}
