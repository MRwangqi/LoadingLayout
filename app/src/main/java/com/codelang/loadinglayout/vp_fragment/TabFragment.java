package com.codelang.loadinglayout.vp_fragment;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.codelang.loadinglayout.loadingUtils.annoation.ErrorClick;
import com.codelang.loadinglayout.R;
import com.codelang.loadinglayout.loadingUtils.WQLoad;
import com.codelang.loadinglayout.loadingUtils.layout.WQLoadingLayout;

/**
 * @author wangqi
 * @since 2018/4/27 下午3:14
 */

public class TabFragment extends BaseFragment {

    WQLoad wqLoad;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    public void lazyLoad() {

        wqLoad = new WQLoad.Builder(getActivity())
                .addLoadingCallBack(new WQLoadingLayout(getActivity()))
                .build();
        wqLoad.init(this, mView);
        wqLoad.showLoadingView();

        mView.findViewById(R.id.tab_tx).postDelayed(new Runnable() {
            @Override
            public void run() {
                wqLoad.showErrorView();
            }
        }, 2000);
    }


    @ErrorClick
    public void pullNet() {
        wqLoad.showLoadingView();
        mView.findViewById(R.id.tab_tx).postDelayed(new Runnable() {
            @Override
            public void run() {
                wqLoad.showContentView();
            }
        }, 2000);
    }


}
