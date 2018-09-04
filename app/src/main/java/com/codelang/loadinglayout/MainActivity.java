package com.codelang.loadinglayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kiwi.loadlayout.WQLoad;
import com.kiwi.loadlayout.annoation.ErrorClick;
import com.kiwi.loadlayout.layout.EmptyLayout;
import com.kiwi.loadlayout.layout.ErrorLayout;
import com.kiwi.loadlayout.layout.LoadingLayout;
import com.codelang.loadinglayout.vp_fragment.TabActivity;

/**
 * @author wangqi
 * @since 2017/12/15 上午8:53
 */

public class MainActivity extends AppCompatActivity {

    WQLoad wqLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wqLoad = new WQLoad.Builder(this)
                .addLoadingCallBack(new LoadingLayout(this))
                .addEmptyView(new EmptyLayout(this))
                .addErrorView(new ErrorLayout(this))
                .build();
        wqLoad.init(this);

        wqLoad.showLoadingView();


        findViewById(R.id.main_content).postDelayed(new Runnable() {
            @Override
            public void run() {
                wqLoad.showErrorView();
            }
        }, 2000);
    }

    @ErrorClick
    public void pullNet() {
        wqLoad.showLoadingView();
        findViewById(R.id.main_content).postDelayed(new Runnable() {
            @Override
            public void run() {
                wqLoad.showContentView();
            }
        }, 2000);
    }


    public void click(View view) {
        startActivity(new Intent(this, TabActivity.class));
    }

}
