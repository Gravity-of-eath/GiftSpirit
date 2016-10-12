package com.yaokun.giftspirit.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.GiftBean;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.List;

/**
 * Created by 姚 坤 on 2016/10/11.
 */
public class MyPagerAdapter extends PagerAdapter {
    List<GiftBean.AdBean> adBeen;
    Context context;

    public MyPagerAdapter(List<GiftBean.AdBean> adBeen, Context context) {
        this.adBeen = adBeen;
        this.context = context;
    }

    public void setAdBeen(List<GiftBean.AdBean> adBeen) {
        this.adBeen = adBeen;
    }

    @Override
    public int getCount() {
        return adBeen == null ? 0 : adBeen.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(Contast.BASE+adBeen.get(position).getIconurl()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
