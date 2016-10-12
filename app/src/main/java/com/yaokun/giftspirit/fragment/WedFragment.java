package com.yaokun.giftspirit.fragment;


import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.activity.Wed_Detail_Activity;
import com.yaokun.giftspirit.adapter.Wed_adapter;
import com.yaokun.giftspirit.bean.WedBean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.http.NetUitls;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WedFragment extends Fragment implements DataBack, AdapterView.OnItemClickListener {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                adapter.setWedBean(bean);
                adapter.notifyDataSetChanged();
                Log.e("WedBean","asdad======="+bean);
            }
        }
    };
   WedBean bean;
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @BindView(R.id.wed_fragment_lv)
    ListView listView;

    Wed_adapter adapter;
    public WedFragment() {
        refresh();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_wed, container, false);
        ButterKnife.bind(this,view);
        adapter=new Wed_adapter(getActivity(),bean);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        refresh();
        return view;
    }

    public void refresh() {
        Log.e("bbbbwedwedwedbb","----"+context);
//        if (NetUitls.isnet((Activity) context)) {
            HttpUtils.GetData(Contast.WED, this);
//        } else {
//            Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void DataBack(String json) {
        bean = Parser.JsonToObj(json, WedBean.class);
        Log.e("WedBean","asdad======="+json);
        handler.obtainMessage(1).sendToTarget();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int sid = bean.getList().get(position).getSid();
        String descs = bean.getList().get(position).getDescs();
        String iconurl = bean.getList().get(position).getIconurl();
        String addtime = bean.getList().get(position).getAddtime();
        Intent i=new Intent(getActivity(), Wed_Detail_Activity.class);
        i.putExtra("id",String.valueOf(sid));
        i.putExtra("descs",descs);
        i.putExtra("iconurl",iconurl);
        i.putExtra("addtime",addtime);
        startActivity(i);
    }
}
