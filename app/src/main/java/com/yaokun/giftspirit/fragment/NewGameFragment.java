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
import android.widget.ListView;
import android.widget.Toast;

import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.activity.NewGame_DetailActivity;
import com.yaokun.giftspirit.adapter.NewGame_Adapter;
import com.yaokun.giftspirit.bean.NewGameBean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.http.NetUitls;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewGameFragment extends Fragment implements DataBack, AdapterView.OnItemClickListener {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                adapter.setBean(bean);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @BindView(R.id.new_game_lv)
    ListView listView;

    NewGameBean bean;

    NewGame_Adapter adapter;

    public NewGameFragment() {
      refresh();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);
        ButterKnife.bind(this, view);
        adapter = new NewGame_Adapter(bean, getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        refresh();
        return view;
    }


    public void refresh() {
//        if (NetUitls.isnet(getActivity())) {
            HttpUtils.GetData(Contast.NEWGAME, this);
            Log.e("bbbbbb","----"+getActivity());
//        } else {
//            Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void DataBack(String json) {
        Log.e("bbbbbb","----"+json);
        bean = Parser.JsonToObj(json, NewGameBean.class);
        handler.obtainMessage(1).sendToTarget();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), NewGame_DetailActivity.class);
        intent.putExtra("id",String.valueOf(adapter.getItem(position).getId()));
        intent.putExtra("titel",(adapter.getItem(position).getName()));
        intent.putExtra("icon",(adapter.getItem(position).getIconurl()));
        intent.putExtra("actoricon",(adapter.getItem(position).getAuthorimg()));
        intent.putExtra("descs",(adapter.getItem(position).getDescs()));

        Log.e("NewGame_DetailActivity","NewGame_DetailActivity===  "+adapter.getItem(position).getId());
        startActivity(intent);
    }
}
