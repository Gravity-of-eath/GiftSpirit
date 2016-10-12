package com.yaokun.giftspirit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yaokun.giftspirit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 姚 坤 on 2016/9/26.
 */
public class Menu_Adapter extends BaseAdapter {
    String[] menu;
    int[] icons;
    Context context;

    public Menu_Adapter(Context context) {
        this.context = context;
        menu=context.getResources().getStringArray(R.array.main_menu);
        icons=new int[]{R.drawable.icon_home,R.drawable.my_gift,R.drawable.send_email,R.drawable.about_me};
        Log.e("getResources","==="+icons.length);
    }

    @Override
    public int getCount() {
        return menu.length;
    }

    @Override
    public String getItem(int position) {
        return menu[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHoder viewHoder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.menu_item,null);
            viewHoder=new ViewHoder(convertView);
        }else {
            viewHoder= (ViewHoder) convertView.getTag();
        }
        viewHoder.itemname.setText(menu[position]);
        viewHoder.icon.setImageResource(icons[position]);
        return convertView;
    }

    class ViewHoder{
        @BindView(R.id.menu_item_name)
        TextView itemname;
        @BindView(R.id.menu_item_icon)
        ImageView icon;
        View view;

        public ViewHoder(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }

}
