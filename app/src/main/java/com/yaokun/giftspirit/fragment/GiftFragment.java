package com.yaokun.giftspirit.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.activity.DetialActivity;
import com.yaokun.giftspirit.adapter.GiftAdapter;
import com.yaokun.giftspirit.adapter.MyPagerAdapter;
import com.yaokun.giftspirit.bean.GiftBean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.http.NetUitls;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GiftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GiftFragment extends Fragment implements DataBack, View.OnClickListener, AdapterView.OnItemClickListener {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1 && listBean != null) {
                if (!IsAdd) {
                    AllBean = listBean;
                    Log.e("IsAdd", "IsAdd");
                } else {
                    AllBean.addAll(AllBean.size(), listBean);
                    Log.e("!!!!IsAdd", "!!!!!!!!!!!IsAdd");
                }
                giftAdapter.setListBean(AllBean);
                giftAdapter.notifyDataSetChanged();
                Log.e("notifyDataSetChanged", "!notifyDataSetChanged" + AllBean.size());
                myPagerAdapter.setAdBeen(ad);
                myPagerAdapter.notifyDataSetChanged();
                myPagerAdapter.notifyDataSetChanged();
                listView.onRefreshComplete();
            }

        }
    };

    @BindView(R.id.fragment_gift_lv)
    PullToRefreshListView listView;
    ListView interlist;
    AutoScrollViewPager viewPager;
    LinearLayout continer;
    int PAGE = 1;
    boolean IsAdd = false;
    View contener;

    List<GiftBean.ListBean> listBean;
    List<GiftBean.ListBean> AllBean;
    Context context;
    MyPagerAdapter myPagerAdapter;
    private GiftAdapter giftAdapter;
    public List<GiftBean.AdBean> ad;

    public void setContext(Context context) {
        this.context = context;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    public GiftFragment() {
        // Required empty public constructor

    }

    public static GiftFragment newInstance(String param1) {
        GiftFragment fragment = new GiftFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        refresh(1);
    }

    public void refresh(int page) {
        if (NetUitls.isnet(getActivity())) {
            HttpUtils.GetData(Contast.GIFT_HOME + page, this);
        } else {
            Toast.makeText(context, "请检查网络", Toast.LENGTH_LONG).show();
            listView.onRefreshComplete();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup tainer,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, tainer, false);
        ButterKnife.bind(this, view);
        giftAdapter = new GiftAdapter(context, AllBean);
        listView.setAdapter(giftAdapter);
        listView.setOnItemClickListener(this);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("放开以获取数据");
        listView.getLoadingLayoutProxy(true, true).setRefreshingLabel("正在拉取数据！");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("上啦加载更多");
        listView.getLoadingLayoutProxy(true, true).setPullLabel("正在拉取数据！");
        View emptyview=View.inflate(getActivity(),R.layout.empty_view,null);
        listView.setEmptyView(emptyview);
        interlist = listView.getRefreshableView();
        contener=View.inflate(context,R.layout.autoviewpager,null);
        viewPager= (AutoScrollViewPager) contener.findViewById(R.id.auto_viewpager);
        continer= (LinearLayout) contener.findViewById(R.id.auto_point);
        myPagerAdapter=new MyPagerAdapter(ad,context);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.startAutoScroll();
        interlist.addHeaderView(contener);
        View foot = View.inflate(context, R.layout.footer, null);
        foot.findViewById(R.id.fotter_tv).setOnClickListener(this);
        interlist.addFooterView(foot);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("setOnRefreshListener", "UUUUUUUUUUUUUUU");
                IsAdd = false;
                PAGE = 1;
                refresh(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("setOnRefreshListener", "DDDDDDDDDDDDDDDDDD");
                IsAdd = true;
                PAGE++;
                refresh(PAGE);
            }
        });
        refresh(1);
        return view;
    }


    @Override
    public void DataBack(String json) {
        GiftBean bean = Parser.JsonToObj(json, GiftBean.class);
        listBean = bean.getList();
        ad = bean.getAd();
        handler.obtainMessage(1).sendToTarget();
        Log.e("refresh", "refresh" + json);
    }

    @Override
    public void onClick(View v) {
        PAGE++;
        refresh(PAGE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position>=2) {
            Intent intent = new Intent(context, DetialActivity.class);
            intent.putExtra("id", AllBean.get(position - 2).getId());
            startActivity(intent);
        }
        Log.e("onItemClick",":===="+position);
    }
}
