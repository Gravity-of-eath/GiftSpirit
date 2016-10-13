package com.yaokun.giftspirit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.HotBean;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 姚 坤 on 2016/9/30.
 */
public class Hot_list_Adapter extends BaseAdapter {

    List<HotBean.InfoBean.Push1Bean> list;
    Context context;

    public Hot_list_Adapter(Context context, List<HotBean.InfoBean.Push1Bean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<HotBean.InfoBean.Push1Bean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HAP hap;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.hot_top_list_item,null);
            hap=new HAP(convertView);
        }else {
            hap= (HAP) convertView.getTag();
        }
        Picasso.with(context).load(Contast.BASE+list.get(position).getLogo()).error(R.drawable.launcher).into(hap.icon);
        hap.name.setText(list.get(position).getName());
        hap.type.setText(list.get(position).getTypename());
        hap.size.setText(list.get(position).getSize());
        hap.num.setText(String.valueOf(list.get(position).getClicks()));
        return convertView;
    }

    class HAP{
        @BindView(R.id.hot_list_icon)
        ImageView icon;
        @BindView(R.id.hot_list_name)
        TextView name;
        @BindView(R.id.hot_type)
        TextView type;
        @BindView(R.id.hot_size)
        TextView size;
        @BindView(R.id.hot_num_text)
        TextView num;
        View view;

        public HAP(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
