package com.codelang.loadinglayout.loadingUtils.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.codelang.loadinglayout.R;
import com.codelang.loadinglayout.loadingUtils.CallBack;

/**
 * @author wangqi
 * @since 2018/4/27 10:39
 */

public class LoadingLayout extends CallBack {


    public LoadingLayout(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.loading_load, null, false);
    }
}
