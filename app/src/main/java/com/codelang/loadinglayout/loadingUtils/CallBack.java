package com.codelang.loadinglayout.loadingUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author wangqi
 * @since 2018/4/27 10:23
 */

public abstract class CallBack {

    Context context;

    public CallBack(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }


    protected abstract View onCreateView();

    public View getRootView() {
        return onCreateView();
    }


}