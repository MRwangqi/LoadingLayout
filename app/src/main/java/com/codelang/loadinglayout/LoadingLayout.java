package com.codelang.loadinglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    /**
     * 空白页面的图片
     */
    private int emptyResource;
    /**
     * 空白页面的文字
     */
    private String strEmptyTxt;


    /**
     * 判断是否显示主布局的标识
     */
    public boolean isContentView = false;


    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingLayout);
        strEmptyTxt = ta.getString(R.styleable.LoadingLayout_empty_txt);
        emptyResource = ta.getResourceId(R.styleable.LoadingLayout_empty_img, R.drawable.empty);
        ta.recycle();


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
        /*****  加载错误页面  *******/
        errorView = inflater.inflate(R.layout.loading_error, this, false);
        addView(errorView);
        /*****  加载空页面  *******/
        emptyView = inflater.inflate(R.layout.loading_empty, this, false);
        addView(emptyView);

        ImageView emptyImg = emptyView.findViewById(R.id.empty_img);
        TextView emptyTxt = emptyView.findViewById(R.id.empty_txt);
        emptyImg.setImageResource(emptyResource);
        emptyTxt.setText(strEmptyTxt);

    }


    public void showContentView() {
        removeView(errorView);
        removeView(emptyView);
        removeView(loadingView);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.VISIBLE);
        }
        isContentView = true;
    }


    public void showErrorView() {
        if (isContentView) return;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        errorView.setVisibility(View.VISIBLE);
    }


    public void showLoadingView() {
        if (isContentView) return;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        loadingView.setVisibility(View.VISIBLE);
    }

    public void showEmptyView() {
        if (isContentView) return;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        emptyView.setVisibility(View.VISIBLE);
    }

}
