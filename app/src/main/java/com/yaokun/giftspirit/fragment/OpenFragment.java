package com.yaokun.giftspirit.fragment;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.adapter.Main_FrgmntPageAdptr;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenFragment extends Fragment {

    Context context;
    @BindView(R.id.open_viewpager)
    ViewPager viewPager;
    @BindView(R.id.open_tablayout)
    TabLayout tabLayout;

    public void setContext(Context context) {
        this.context = context;
    }

    public OpenFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_open, container, false);
        ButterKnife.bind(this,view);
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new Open_Open());
        fragments.add(new Open_Test());
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.main_titel_bg)));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("开服"));
        tabLayout.addTab(tabLayout.newTab().setText("开测"));
        viewPager.setAdapter(new Main_FrgmntPageAdptr(getChildFragmentManager(),fragments));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
