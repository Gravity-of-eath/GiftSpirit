package com.yaokun.giftspirit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.GiftBean;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 姚 坤 on 2016/9/26.
 */
public class GiftAdapter extends BaseAdapter {

    List<GiftBean.ListBean> listBean;
    Context context;

    public void setListBean(List<GiftBean.ListBean> listBean) {
        this.listBean = listBean;
    }

    public GiftAdapter(Context context, List<GiftBean.ListBean> bean) {
        this.context = context;
        this.listBean = bean;
    }

    @Override
    public int getCount() {
        return listBean==null ?0:listBean.size();
    }

    @Override
    public Object getItem(int position) {
        return listBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.gift_item,null);
            viewHoder=new ViewHoder(convertView);
        }else {
            viewHoder= (ViewHoder) convertView.getTag();
        }
        GiftBean.ListBean bean=listBean.get(position);
        Picasso.with(context).load(Contast.BASE+bean.getIconurl()).into(viewHoder.icon);
        viewHoder.name.setText(bean.getGname());
        viewHoder.type.setText(bean.getGiftname());
        viewHoder.surplus.setText(String.valueOf(bean.getNumber()));
        viewHoder.time.setText(bean.getAddtime());
        if(bean.getNumber()<=0){
            viewHoder.btn.setBackground(context.getResources().getDrawable(R.drawable.btn_gb_gray));
            viewHoder.btn.setText("淘号");
        }else {
            viewHoder.btn.setBackground(context.getResources().getDrawable(R.drawable.btn_bd));
            viewHoder.btn.setText("立即领取");
        }

        return convertView;
    }

    class ViewHoder{
        @BindView(R.id.gift_item_icon)
        ImageView icon;
        @BindView(R.id.gift_item_name)
        TextView name;
        @BindView(R.id.gift_item_type)
        TextView type;
        @BindView(R.id.gift_item_surplus)
        TextView surplus;
        @BindView(R.id.gift_item_time)
        TextView time;
        @BindView(R.id.gift_item_getnow)
        Button btn;
        View view;

        public ViewHoder(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
