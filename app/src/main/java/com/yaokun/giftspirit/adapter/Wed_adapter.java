package com.yaokun.giftspirit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.WedBean;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 姚 坤 on 2016/10/6.
 */
public class Wed_adapter extends BaseAdapter {

    WedBean wedBean;
    Context context;

    public Wed_adapter(Context context, WedBean wedBean) {
        this.context = context;
        this.wedBean = wedBean;
    }

    public void setWedBean(WedBean wedBean) {
        this.wedBean = wedBean;
    }

    @Override
    public int getCount() {
        return wedBean == null ? 0 : wedBean.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return wedBean.getList().get(position);
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
        Picasso.with(context).load(Contast.BASE+wedBean.getList().get(position).getIconurl()).error(R.drawable.launcher).into(vh.imageView);
        vh.textView.setText(wedBean.getList().get(position).getName());
        vh.time.setText(wedBean.getList().get(position).getAddtime());
        vh.iv_time.setImageResource(R.drawable.g_biao);

        return convertView;
    }

    class VH {
        @BindView(R.id.wed_iv_bg)
        ImageView imageView;
        @BindView(R.id.wed_titel)
        TextView textView;
        @BindView(R.id.wed_iv_time)
        ImageView iv_time;

        @BindView(R.id.wed_time)
        TextView time;
        View view;

        public VH(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
            view.setTag(this);

        }
    }
}
