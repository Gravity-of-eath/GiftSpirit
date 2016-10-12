package com.yaokun.giftspirit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 姚 坤 on 2016/10/6.
 */
public class FeatureAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;

    public FeatureAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "暴打星期三" : "新游周刊";
    }
}
