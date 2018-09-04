package com.codelang.loadinglayout.vp_fragment;


import android.view.View;
import android.widget.TextView;

import com.codelang.loadinglayout.WQLoadingLayout;
import com.kiwi.loadlayout.WQLoad;
import com.kiwi.loadlayout.annoation.ErrorClick;
import com.codelang.loadinglayout.R;

/**
 * @author wangqi
 * @since 2018/4/27 下午3:14
 */

public class TabFragment extends BaseFragment {

    WQLoad wqLoad;
    TextView txContent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    public void lazyLoad() {
        txContent = mView.findViewById(R.id.tab_tx);

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
                txContent.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }


}
