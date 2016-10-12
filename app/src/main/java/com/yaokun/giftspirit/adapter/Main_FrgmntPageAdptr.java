package com.yaokun.giftspirit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 姚 坤 on 2016/9/27.
 */
public class Main_FrgmntPageAdptr extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    public Main_FrgmntPageAdptr(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position==0? "开服":"开测";
    }
}
