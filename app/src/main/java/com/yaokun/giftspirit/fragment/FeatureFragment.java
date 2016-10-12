package com.yaokun.giftspirit.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.adapter.FeatureAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeatureFragment extends Fragment {


    @BindView(R.id.feature_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.feature_viewpager)
    ViewPager viewPager;
    List<Fragment> fragmentList;
    FeatureAdapter adapter;
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public FeatureFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feature, container, false);
        ButterKnife.bind(this, view);
        fragmentList = new ArrayList<>();
        WedFragment wedFragment = new WedFragment();
        wedFragment.setContext(context);
        Log.e("bFeatureFragmentbb"+context,"----"+getActivity());

        fragmentList.add(wedFragment);
        fragmentList.add(new NewGameFragment());
        adapter = new FeatureAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
