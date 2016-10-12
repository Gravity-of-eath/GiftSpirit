package com.yaokun.giftspirit.Parser;

import android.text.format.DateFormat;
import android.util.Log;

import com.yaokun.giftspirit.bean.OpenBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 姚 坤 on 2016/9/29.
 */
public class SortUtils {

    public static LinkedHashMap<String, List<OpenBean.InfoBean>> SortToGroup(OpenBean bean) {

        LinkedHashMap<String, List<OpenBean.InfoBean>> map = new LinkedHashMap<>();
        List<OpenBean.InfoBean> list = bean.getInfo();
        Collections.sort(list, new Comparator<OpenBean.InfoBean>() {
            @Override
            public int compare(OpenBean.InfoBean lhs, OpenBean.InfoBean rhs) {
                return lhs.getAddtime().compareTo(rhs.getAddtime());
            }
        });
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(date);

        String time = "1";
        ArrayList<OpenBean.InfoBean> value = null;
        for (OpenBean.InfoBean b : list) {
            if (time.equals(b.getAddtime())) {
                value.add(b);
                Log.e("SortUtils", "SortUtils====equals==" + time);

            } else {
                time = b.getAddtime();
                value = new ArrayList<>();
                value.add(b);
                if (today.equals(time)) {
                    map.put(time+"（今日开服）", value);
                } else {
                    map.put(time, value);
                }
            }
            Log.e("SortUtils" + list.size(), "SortUtils======" + time);
        }
        Log.e("SortUtils", "SortUtils======" + map.size());

        return map;
    }


}
