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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.activity.SecondActivity;
import com.yaokun.giftspirit.adapter.Hot_GridView_Adapter;
import com.yaokun.giftspirit.adapter.Hot_list_Adapter;
import com.yaokun.giftspirit.bean.HotBean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment implements DataBack{

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            list_adapter.setList(hotBean.getInfo().getPush1());
            gridView_adapter.setList(hotBean.getInfo().getPush2());
            list_adapter.notifyDataSetChanged();
            gridView_adapter.notifyDataSetChanged();
        }
    };

    Hot_GridView_Adapter gridView_adapter;
    Hot_list_Adapter list_adapter;
    @BindView(R.id.hot_btom_gv)
    GridView gridView;
    @BindView(R.id.hot_top_lv)
    ListView listView;
    HotBean hotBean;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this,view);
        HttpUtils.GetData(Contast.HOT,this);
        gridView_adapter=new Hot_GridView_Adapter(getActivity(),null);
        gridView.setAdapter(gridView_adapter);
        list_adapter=new Hot_list_Adapter(getActivity(),null);
        listView.setAdapter(list_adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stardActivity(hotBean.getInfo().getPush2().get(position).getAppid());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stardActivity(hotBean.getInfo().getPush1().get(position).getAppid());

            }
        });
        return view;
    }

    public void stardActivity(String gid){
        Log.e("gidgidgidgidgid","gidgidgid"+gid);
        Intent intent=new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("gid",gid);
        startActivity(intent);
    }


    @Override
    public void DataBack(String json) {
        hotBean=Parser.JsonToObj(json,HotBean.class);
        handler.obtainMessage(0).sendToTarget();
    }
}
