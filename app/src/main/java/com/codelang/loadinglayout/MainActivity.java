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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingLayout = (LoadingLayout) findViewById(R.id.main_loading);
        loadingLayout.showLoadingView();
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.main_content:
                loadingLayout.showContentView();
                break;
            case R.id.main_empty:
                loadingLayout.showEmptyView();
                break;
            case R.id.main_error:
                loadingLayout.showErrorView();
                break;
            case R.id.main_load:
                loadingLayout.showLoadingView();
                break;
            default:
        }
    }
}
