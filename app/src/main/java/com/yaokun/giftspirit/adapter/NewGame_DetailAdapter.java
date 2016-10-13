package com.yaokun.giftspirit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.NewGameBean;
import com.yaokun.giftspirit.bean.NewGame_DetailBean;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 姚 坤 on 2016/10/7.
 */
public class NewGame_DetailAdapter extends BaseAdapter {

    NewGame_DetailBean bean;
    Context context;

    public NewGame_DetailAdapter(NewGame_DetailBean bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    public void setBean(NewGame_DetailBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getList().size();
    }

    @Override
    public NewGame_DetailBean.ListBean getItem(int position) {
        return bean.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.newgame_detail_item,null);
            vh=new VH(convertView);
        }else {
            vh= (VH) convertView.getTag();
        }
        Picasso.with(context).load(Contast.BASE+bean.getList().get(position).getIconurl()).error(R.drawable.launcher).into(vh.icon);
        vh.name.setText(bean.getList().get(position).getAppname());
        vh.type.setText(bean.getList().get(position).getTypename());
        vh.size.setText(bean.getList().get(position).getAppsize());
        vh.shuoming.setText(bean.getList().get(position).getDescs());
        return convertView;
    }

    class VH{
        @BindView(R.id.newgame_item_icon)
        ImageView icon;
        @BindView(R.id.newgame_item_name)
        TextView name;
        @BindView(R.id.newgame_item_type)
        TextView type;
        @BindView(R.id.newgame_item_size)
        TextView size;
        @BindView(R.id.newgame_item_shengyu)
        TextView shuoming;
        View view;

        public VH(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
