package com.yaokun.giftspirit.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.Wed_Detailbean;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 姚 坤 on 2016/10/7.
 */
public class Wed_Detail_Adapter extends BaseAdapter {
    Wed_Detailbean wed_detailbean;
    Context context;

    public Wed_Detail_Adapter(Context context, Wed_Detailbean wed_detailbean) {
        this.context = context;
        this.wed_detailbean = wed_detailbean;
    }

    public void setWed_detailbean(Wed_Detailbean wed_detailbean) {
        this.wed_detailbean = wed_detailbean;
    }

    @Override
    public int getCount() {
        return wed_detailbean == null ? 0 : wed_detailbean.getList().size();
    }

    @Override
    public Wed_Detailbean.ListBean getItem(int position) {
        return wed_detailbean.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView==null){
            convertView=View .inflate(context,R.layout.wed_detail_item,null);
            vh=new VH(convertView);
        }else {
            vh= (VH) convertView.getTag();
        }
        Picasso.with(context).load(Contast.BASE+wed_detailbean.getList().get(position).getAppicon()).error(R.drawable.launcher).into(vh.icon);
        vh.name.setText(wed_detailbean.getList().get(position).getAppname());
        return convertView;
    }

    class VH{
        @BindView(R.id.wed_detail_item_icon)
        ImageView icon;
        @BindView(R.id.wed_detail_item_name)
        TextView name;
        @BindView(R.id.wed_detail_item_btn)
        Button btn;
        View view;

        public VH(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }

}
