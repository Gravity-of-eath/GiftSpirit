package com.yaokun.giftspirit.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by 姚 坤 on 2016/10/7.
 */
public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(1000000,MeasureSpec.AT_MOST));
    }
}
