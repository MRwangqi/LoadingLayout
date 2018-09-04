package com.codelang.loadinglayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.kiwi.loadlayout.CallBack;

public class WQLoadingLayout extends CallBack {

    public WQLoadingLayout(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.loading_load_wq, null, false);
    }
}
