package com.yaokun.giftspirit.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.Parser.SortUtils;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.activity.SecondActivity;
import com.yaokun.giftspirit.adapter.Open_open_ExpAdapter;
import com.yaokun.giftspirit.bean.OpenBean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.http.NetUitls;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Open_Open extends Fragment implements DataBack, ExpandableListView.OnGroupExpandListener {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                adapter = new Open_open_ExpAdapter(getActivity(), map);
                expandableListView.setAdapter(adapter);
                opengroup();
            }

        }
    };

    LinkedHashMap<String, List<OpenBean.InfoBean>> map;
    Open_open_ExpAdapter adapter;
    @BindView(R.id.open_open_explistview)
    ExpandableListView expandableListView;

    public Open_Open() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open__open, container, false);
        ButterKnife.bind(this, view);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupExpandListener(this);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Iterator<String> iterator = map.keySet().iterator();

                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("gid", adapter.getChild(groupPosition, childPosition).getGid());
                startActivity(intent);
                return false;
            }
        });
        refresh();
        return view;
    }

    public void opengroup() {
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
    }


    public void refresh() {
        if (NetUitls.isnet(getActivity())) {
            HttpUtils.GetData(Contast.OPEN_OPEN, this);
        } else {
            Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void DataBack(String json) {
        OpenBean openBean = Parser.JsonToObj(json, OpenBean.class);
        Log.e("Open_Open","Open_Open=== "+openBean.getInfo().size());
        map = SortUtils.SortToGroup(openBean);
        handler.obtainMessage(0).sendToTarget();
    }

    @Override
    public void onGroupExpand(int groupPosition) {

    }
}
