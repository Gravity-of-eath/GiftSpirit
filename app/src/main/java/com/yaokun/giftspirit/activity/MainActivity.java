package com.yaokun.giftspirit.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.adapter.Main_FrgmntPageAdptr;
import com.yaokun.giftspirit.adapter.Menu_Adapter;
import com.yaokun.giftspirit.bean.GiftBean;
import com.yaokun.giftspirit.fragment.FeatureFragment;
import com.yaokun.giftspirit.fragment.GiftFragment;
import com.yaokun.giftspirit.fragment.HotFragment;
import com.yaokun.giftspirit.fragment.OpenFragment;
import com.yaokun.giftspirit.fragment.Open_Open;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @BindView(R.id.main_toolbar_titel)
    TextView titel;
    @BindView(R.id.main_botton_rg)
    RadioGroup btom_rg;
    @BindView(R.id.main_slidpanel)
    SlidingPaneLayout slidingPaneLayout;
    @BindView(R.id.main_content_rl)
    RelativeLayout relativeLayout;
    @BindView(R.id.main_menu_list)
    ListView menu;
    @BindView(R.id.main_fragment_continer)
    LinearLayout content;

    GiftBean giftBean;
    int pagenum = 1;
    GiftFragment giftFragment;
    OpenFragment openFragment;
    HotFragment hotFragment;
    FeatureFragment featureFragment;
    FragmentManager manager;
    long centertime = 0;
    Fragment cencreFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();
        menu.setAdapter(new Menu_Adapter(this));
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(R.color.transfor));
        slidingPaneLayout.setCoveredFadeColor(getResources().getColor(R.color.transfor));
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                relativeLayout.setScaleY((float) (1 - (0.2 * slideOffset)));
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });

        giftFragment = GiftFragment.newInstance("kk");
        giftFragment.setContext(MainActivity.this);
        openFragment = new OpenFragment();
        openFragment.setContext(this);
        hotFragment = new HotFragment();
        featureFragment = new FeatureFragment();
        featureFragment.setContext(this);
        showFragment(giftFragment);

        btom_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_btom_rb_gift:
                        showFragment(giftFragment);
                        titel.setText("礼物精灵");
                        break;
                    case R.id.main_btom_rb_open:
                        showFragment(openFragment);
                        titel.setText("开服");
                        break;
                    case R.id.main_btom_rb_hot:
                        showFragment(hotFragment);
                        titel.setText("热门");
                        break;
                    case R.id.main_btom_rb_feature:
                        showFragment(featureFragment);
                        titel.setText("特色");
                        break;
                }

            }
        });

    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (cencreFragment != null) {
            transaction.hide(cencreFragment);
        }
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.main_fragment_continer, fragment);
        }


        cencreFragment = fragment;
        transaction.commit();
    }


    @OnClick(R.id.main_toolbar_menu)
    void Swich() {
        if (slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else {
            slidingPaneLayout.openPane();
        }

    }


    @Override
    public void onBackPressed() {
        long time = System.currentTimeMillis();
        if (time - centertime < 1500) {
            this.finish();
        } else {
            centertime = time;
            Toast.makeText(this, "再按一次退出！", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.main_toolbar_search)
    void Search() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

}
