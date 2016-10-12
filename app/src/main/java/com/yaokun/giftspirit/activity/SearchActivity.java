package com.yaokun.giftspirit.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.adapter.GiftAdapter;
import com.yaokun.giftspirit.bean.GiftBean;
import com.yaokun.giftspirit.bean.SearchDataBean;
import com.yaokun.giftspirit.fileutils.FileUtils;
import com.yaokun.giftspirit.http.SearchData;
import com.yaokun.giftspirit.http.SearchUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            resultlist.setAdapter(new GiftAdapter(SearchActivity.this, giftBean.getList()));
            resultlist.setOnItemClickListener(SearchActivity.this);
        }
    };


    List<String> keywords;
    @BindView((R.id.search_toolbar_searchview))
    AutoCompleteTextView searchView;
    @BindView(R.id.search_toolbar_search_icon)
    ImageView icon;
    @BindView(R.id.search_list)
    ListView resultlist;
    private GiftBean giftBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }


    @OnClick(R.id.search_toolbar_back)
    void back() {
        this.finish();
    }

    @OnClick(R.id.main_toolbar_search)
    void search() {
        final String keyword = searchView.getText().toString();
        if (!TextUtils.isEmpty(keyword)) {
            keywords.add(keyword);
        }
        try {
            Log.e("getSearchData", "===" + keyword);
            final String encode = URLEncoder.encode(keyword, "utf-8");
            SearchUtils.getSearchUtils().getSearchData(keyword).enqueue(new Callback<GiftBean>() {
                @Override
                public void onResponse(Call<GiftBean> call, Response<GiftBean> response) {
                    giftBean = response.body();
                    Log.e("getSearchData", "==="+encode);
                    handler.obtainMessage(0).sendToTarget();
                }

                @Override
                public void onFailure(Call<GiftBean> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        keywords = FileUtils.ReadStringListFromFile(this, "searchHistory");
        if (keywords != null) {
            searchView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keywords));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        FileUtils.SaveStringListToFile(this, "searchHistory", keywords);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetialActivity.class);
        intent.putExtra("id", giftBean.getList().get(position).getId());
        startActivity(intent);
    }
}
