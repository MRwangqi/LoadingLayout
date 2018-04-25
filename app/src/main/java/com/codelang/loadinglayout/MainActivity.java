package com.codelang.loadinglayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author wangqi
 * @since 2017/12/15 上午8:53
 */

public class MainActivity extends AppCompatActivity {

    LoadingLayout loadingLayout;
    int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingLayout = (LoadingLayout) findViewById(R.id.main_loading);
        type = 1;
        pullNet();
    }


    public void click(View v) {
        switch (v.getId()) {
            case R.id.main_content:
                type = 1;
                pullNet();
                break;
            case R.id.main_empty:
                type = 2;
                pullNet();
                break;
            case R.id.main_error:
                type = 3;
                pullNet();
                break;
            case R.id.main_reset:
                loadingLayout.reset();
            default:
        }
    }


    /**
     * 模拟请求网络
     */
    @ErrorClick
    public void pullNet() {

        if (type == 1) {
            //执行请求网络操作  开始家在正在加载的布局
            loadingLayout.showLoadingView();
            //模拟请求网络加载3秒钟
            loadingLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //模拟请求成功
                    loadingLayout.showContentView();
                }
            }, 3000);
        }


        if (type == 2) {

            loadingLayout.showLoadingView();
            loadingLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //模拟请求成功  数据为空
                    loadingLayout.showEmptyView();
                }
            }, 3000);

        }


        if (type == 3) {
            loadingLayout.showLoadingView();
            loadingLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //模拟请求失败
                    loadingLayout.showErrorView();
                }
            }, 3000);
        }


    }
}
