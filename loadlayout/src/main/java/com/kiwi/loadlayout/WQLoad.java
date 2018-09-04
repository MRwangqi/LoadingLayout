package com.kiwi.loadlayout;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kiwi.loadlayout.annoation.ErrorClick;
import com.kiwi.loadlayout.layout.EmptyLayout;
import com.kiwi.loadlayout.layout.ErrorLayout;
import com.kiwi.loadlayout.layout.LoadingLayout;

import java.lang.reflect.Method;

/**
 * @author wangqi
 * @since 2018/4/27 10:21
 */

public class WQLoad {

    private Builder builder;

    public WQLoad(Builder builder) {
        this.builder = builder;
    }

    public static class Builder {

        CallBack emptyCallBack;
        CallBack errorCallBack;
        CallBack loadingCallBack;
        public Builder(Context context) {
            emptyCallBack = new EmptyLayout(context);
            errorCallBack = new ErrorLayout(context);
            loadingCallBack = new LoadingLayout(context);
        }

        public Builder addEmptyView(CallBack emptyCallBack) {
            this.emptyCallBack = emptyCallBack;
            return this;
        }

        public Builder addErrorView(CallBack errorCallBack) {
            this.errorCallBack = errorCallBack;
            return this;
        }

        public Builder addLoadingCallBack(CallBack loadingCallBack) {
            this.loadingCallBack = loadingCallBack;
            return this;
        }

        public WQLoad build() {
            return new WQLoad(this);
        }
    }

    /**
     * 父布局
     */
    private ViewGroup contentParent;
    /**
     * 当前布局
     */
    private View contentView;

    /**
     * 初始化对应的类
     */
    private Object clazz;


    /**
     * 是否已显示成功布局
     */
    boolean isShowContent;


    View emptyView;
    View errorView;
    View loadingView;


    public void init(Object clazz) {
        init(clazz, null);
    }

    public void init(Object clazz, View mView) {
        this.clazz = clazz;
        //如果当前初始化环境是Activity
        if (clazz instanceof Activity && mView == null) {
            Activity activity = (Activity) clazz;
            //拿到Activity的父布局
            contentParent = activity.findViewById(android.R.id.content);
            //拿到Activity的子布局，Activity就一个子布局，就是setContentView的根布局
            contentView = contentParent.getChildAt(0);
        } else if (clazz != null && mView != null) {
            contentParent = (ViewGroup) mView;
        } else {
            return;
        }


        emptyView = builder.emptyCallBack.getRootView();
        loadingView = builder.loadingCallBack.getRootView();
        errorView = builder.errorCallBack.getRootView();

        //添加View
        addView(contentParent, emptyView);
        addView(contentParent, loadingView);
        addView(contentParent, errorView);


        //加载错误布局的点击事件
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickErrorLayout();
            }
        });

    }


    public void showContentView() {

        contentParent.removeView(errorView);
        contentParent.removeView(loadingView);
        contentParent.removeView(emptyView);

        for (int i = 0; i < contentParent.getChildCount(); i++) {
            contentParent.getChildAt(i).setVisibility(View.VISIBLE);
        }

        isShowContent = true;
    }

    public void showErrorView() {
        if (isShowContent) return;

        for (int i = 0; i < contentParent.getChildCount(); i++) {
            contentParent.getChildAt(i).setVisibility(View.GONE);
        }
        loadingView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    public void showLoadingView() {
        if (isShowContent) return;

        for (int i = 0; i < contentParent.getChildCount(); i++) {
            contentParent.getChildAt(i).setVisibility(View.GONE);
        }
        errorView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);

    }


    private void clickErrorLayout() {
        Class clz = clazz.getClass();
        try {
            Method[] methods = clz.getDeclaredMethods();
            for (Method m : methods) {
                if (m.getAnnotation(ErrorClick.class) != null) {
                    m.setAccessible(true);
                    m.invoke(clazz);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addView(ViewGroup parentView, View rootView) {
        parentView.addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
