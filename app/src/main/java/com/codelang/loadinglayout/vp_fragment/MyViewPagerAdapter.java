package com.codelang.loadinglayout.vp_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by huleiyang
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList;
    private String title[];

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String title[]) {
        super(fm);
        this.fragmentList = fragmentList;
        this.title = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title != null ? title[position] : super.getPageTitle(position);
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
