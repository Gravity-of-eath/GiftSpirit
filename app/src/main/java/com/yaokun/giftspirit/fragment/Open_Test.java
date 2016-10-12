package com.yaokun.giftspirit.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.activity.SecondActivity;
import com.yaokun.giftspirit.adapter.Open_Test_Adapter;
import com.yaokun.giftspirit.bean.OpenTest;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.http.NetUitls;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Open_Test extends Fragment implements DataBack {

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                test_adapter.setOpen_test(testbean);
                test_adapter.notifyDataSetChanged();
                open_test_listview.onRefreshComplete();
            }
        }
    };

    OpenTest testbean;
    @BindView(R.id.open_test_listview)
    PullToRefreshListView open_test_listview;
    Open_Test_Adapter test_adapter;
    public Open_Test() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_open__test, container, false);
        ButterKnife.bind(this,view);
        test_adapter=new Open_Test_Adapter(getActivity(),testbean);
        open_test_listview.setAdapter(test_adapter);
        open_test_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        open_test_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("gid",test_adapter.getItem(position-1).getGid());
                startActivity(intent);
            }
        });
        refresh();
        return view;
    }

    public void refresh() {
        if (NetUitls.isnet(getActivity())) {
            HttpUtils.GetData(Contast.OPEN_TEST, this);
        } else {
            Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void DataBack(String json) {
        testbean = Parser.JsonToObj(json, OpenTest.class);
        handler.obtainMessage(0).sendToTarget();
    }
}
