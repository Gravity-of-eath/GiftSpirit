package com.yaokun.giftspirit.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.OpenBean;
import com.yaokun.giftspirit.bean.OpenTest;
import com.yaokun.giftspirit.fragment.Open_Test;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 姚 坤 on 2016/9/29.
 */
public class Open_Test_Adapter extends BaseAdapter {

    OpenTest opentest;
    Context context;

    public Open_Test_Adapter(Context context, OpenTest opentest) {
        this.context = context;
        this.opentest = opentest;
    }


    public void setOpen_test(OpenTest opentest) {
        this.opentest = opentest;
    }

    @Override
    public int getCount() {
        return opentest == null ? 0 : opentest.getInfo().size();
    }

    @Override
    public OpenTest.InfoBean getItem(int position) {
        return opentest.getInfo().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       VH vh;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.open_test_item,null);
            vh=new VH(convertView);
        }else{
            vh= (VH) convertView.getTag();
        }
       List<OpenTest.InfoBean> list= opentest.getInfo();
        Picasso.with(context).load(Contast.BASE+list.get(position).getIconurl()).error(R.drawable.launcher).into(vh.icon);
        vh.name.setText(list.get(position).getGname());
        vh.yunyings.setText(list.get(position).getOperators());
        vh.time.setText(list.get(position).getAddtime());
        return convertView;
    }

    class VH{
        @BindView(R.id.test_child_icon)
        ImageView icon;
        @BindView(R.id.test_child_name)
        TextView name;
        @BindView(R.id.test_yunys_name)
        TextView yunyings;
        @BindView(R.id.test_time)
        TextView time;
        View view;

        public VH(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            this.view.setTag(this);
        }
    }
}
