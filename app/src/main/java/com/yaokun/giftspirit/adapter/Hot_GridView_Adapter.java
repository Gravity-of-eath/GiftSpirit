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
public class Hot_GridView_Adapter extends BaseAdapter {

    List<HotBean.InfoBean.Push2Bean> list;
    Context context;

    public Hot_GridView_Adapter(Context context, List<HotBean.InfoBean.Push2Bean> list) {
        this.context = context;
        this.list = list;
    }


    public void setList(List<HotBean.InfoBean.Push2Bean> list) {
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
        GVH gvh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.gride_item, null);
            gvh = new GVH(convertView);
        } else {
            gvh = (GVH) convertView.getTag();
        }
        Picasso.with(context).load(Contast.BASE+list.get(position).getLogo()).error(R.drawable.launcher).into(gvh.icon);
        gvh.textView.setText(list.get(position).getName());

        return convertView;
    }

    class GVH {
        @BindView(R.id.gride_img)
        ImageView icon;
        @BindView(R.id.gride_text)
        TextView textView;
        View view;

        public GVH(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
