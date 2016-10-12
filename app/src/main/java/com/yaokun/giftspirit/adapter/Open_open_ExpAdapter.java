package com.yaokun.giftspirit.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.OpenBean;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 姚 坤 on 2016/9/28.
 */
public class Open_open_ExpAdapter implements ExpandableListAdapter {

    LinkedHashMap<String, List<OpenBean.InfoBean>> listHashMap;
    List<String> times;
    Context context;

    public Open_open_ExpAdapter(Context context, LinkedHashMap<String, List<OpenBean.InfoBean>> listHashMap) {
        this.context = context;
        this.listHashMap = listHashMap;
        Iterator<String> iterator = this.listHashMap.keySet().iterator();
        times = new ArrayList<>();
        Iterator iter = this.listHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            times.add(key);
            Log.e("times", "times=============" + key);
        }

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return times == null ? 0 : times.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(times.get(groupPosition)) == null ? 0 : listHashMap.get(times.get(groupPosition)).size();
    }

    @Override
    public List<OpenBean.InfoBean> getGroup(int groupPosition) {
        Log.e("Open_open_ExpAdapter","Open_open_ExpAdapter=="+listHashMap.get(times.get(groupPosition)).size());
        return listHashMap.get(times.get(groupPosition));
    }

    @Override
    public OpenBean.InfoBean getChild(int groupPosition, int childPosition) {
        return listHashMap.get(times.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GVH gvh = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.open_group_iten, null);
            gvh = new GVH(convertView);
        } else {
            gvh = (GVH) convertView.getTag();
        }
        gvh.dates.setText(times.get(groupPosition));

        return convertView;
    }

    class GVH {
        @BindView(R.id.open_group_time)
        TextView dates;
        View view;

        public GVH(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CVH cvh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.open_child_item, null);
            cvh = new CVH(convertView);
        } else {
            cvh = (CVH) convertView.getTag();
        }
        OpenBean.InfoBean bean = listHashMap.get(times.get(groupPosition)).get(childPosition);
        Picasso.with(context).load(Contast.BASE+bean.getIconurl()).into(cvh.icon);
        cvh.name.setText(bean.getGname());
        Log.e("Open_open_ExpAdapter", "getChildView==" +listHashMap.get(times.get(groupPosition)).size());
        cvh.time.setText(bean.getStarttime());
        cvh.provider.setText(bean.getOperators());
        cvh.service.setText(bean.getArea());
        return convertView;
    }

    class CVH {
        @BindView(R.id.open_child_icon)
        ImageView icon;
        @BindView(R.id.open_child_name)
        TextView name;
        @BindView(R.id.open_child_time)
        TextView time;
        @BindView(R.id.open_child_provider_name)
        TextView provider;
        @BindView(R.id.open_child_service)
        TextView service;
        View view;

        public CVH(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
