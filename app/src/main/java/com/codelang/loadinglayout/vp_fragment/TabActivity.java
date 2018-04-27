package com.codelang.loadinglayout.vp_fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codelang.loadinglayout.R;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class TabActivity extends FragmentActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    List<Fragment> list = new ArrayList<>();
    String[] title = new String[]{"普通会员", "白银会员", "黄金会员", "铂金会员", "钻石会员", "黑钻会员"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        initFragmentList();
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), list, title));
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initFragmentList() {
        for (int i = 0; i < title.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
            TabFragment rankFragment = new TabFragment();
            Bundle b = new Bundle();
            b.putInt("index", i);
            rankFragment.setArguments(b);
            list.add(rankFragment);
        }
    }


}
