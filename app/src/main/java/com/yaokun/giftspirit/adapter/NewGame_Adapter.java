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
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 姚 坤 on 2016/10/6.
 */
public class NewGame_Adapter extends BaseAdapter {

    Context context;
    NewGameBean bean;

    public NewGame_Adapter(NewGameBean bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    public void setBean(NewGameBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getList().size();
    }

    @Override
    public NewGameBean.ListBean getItem(int position) {
        return bean.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.wed_newgame_item, null);
            vh = new VH(convertView);
        } else {
            vh = (VH) convertView.getTag();
        }
        Picasso.with(context).load(Contast.BASE + bean.getList().get(position).getIconurl()).error(R.drawable.launcher).into(vh.imageView);
        Picasso.with(context).load(Contast.BASE + "/" + bean.getList().get(position).getAuthorimg()).error(R.drawable.launcher).into(vh.circleImageView);
        vh.textView.setText(bean.getList().get(position).getName());
        return convertView;
    }

    class VH {
        @BindView(R.id.wed_iv_bg)
        ImageView imageView;
        @BindView(R.id.wed_titel)
        TextView textView;
        @BindView(R.id.wed_icon)
        CircleImageView circleImageView;
        View view;

        public VH(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
            view.setTag(this);

        }
    }
}
