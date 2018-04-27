package com.codelang.loadinglayout.vp_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author wangqi
 * @since 2018/4/27 10:07
 */

public abstract class BaseFragment extends Fragment {

    View mView;
    boolean isLoad = false;
    boolean isInit = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mView == null)
            mView = inflater.inflate(getLayoutId(), container, false);
        isInit = true;
        isCanLoadData();
        return mView;
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 懒加载
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint() && !isLoad) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
    }


    /**
     * 第一次加载的处理   此处可以留给加载网络去处理
     */
    public void lazyLoad() {

    }

    /**
     * 页面停止加载的处理
     */
    public void stopLoad() {

    }

    public abstract int getLayoutId();

}
