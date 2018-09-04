package com.kiwi.loadlayout.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.kiwi.loadlayout.CallBack;
import com.kiwi.loadlayout.R;

/**
 * @author wangqi
 * @since 2018/4/27 10:39
 */

public class LoadingLayout extends CallBack {


    public LoadingLayout(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.loading_load, null, false);
    }


}
