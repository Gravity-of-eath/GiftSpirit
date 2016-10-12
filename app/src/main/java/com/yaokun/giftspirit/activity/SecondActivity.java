package com.yaokun.giftspirit.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.SecondBean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.inteface.Contast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity implements DataBack {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            titel.setText(secondBean.getApp().getName());
            Picasso.with(SecondActivity.this).load(Contast.BASE + secondBean.getApp().getLogo()).into(icon);
            name.setText(secondBean.getApp().getName());
            type.setText(secondBean.getApp().getTypename());
            String sizenum = secondBean.getApp().getAppsize();
            String download_addr = secondBean.getApp().getDownload_addr();
            if(TextUtils.isEmpty(download_addr)){
                downlode.setText("暂无下载");
                downlode.setBackground(getResources().getDrawable(R.drawable.btn_gb_gray));
                downlode.setEnabled(false);
            }
            if ("".equals(sizenum)) {
                size.setText("未知");
            } else {
                size.setText(sizenum);
            }
            notify.setText(secondBean.getApp().getDescription());
            showImg(secondBean.getImg());
        }
    };

    @BindView(R.id.second_toolbar_titel)
    TextView titel;
    @BindView(R.id.second_icon)
    ImageView icon;
    @BindView(R.id.second_game_name)
    TextView name;
    @BindView(R.id.second_type)
    TextView type;
    @BindView(R.id.second_size)
    TextView size;
    @BindView(R.id.second_game_notify)
    TextView notify;
    @BindView(R.id.second_btn)
    Button downlode;
    @BindView(R.id.second_img_contener)
    LinearLayout contener;

    SecondBean secondBean;

    DisplayMetrics metrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        Intent intent = getIntent();
//        Log.e("SecondActivity", "======" + Contast.OPEN_TEST_DETAIL + intent.getStringExtra("gid"));
        String gid = intent.getStringExtra("gid");
        if (!TextUtils.isEmpty(gid)) {
            HttpUtils.GetData(Contast.OPEN_TEST_DETAIL + gid, this);
        } else {
            Toast.makeText(this, "暂无APP信息", Toast.LENGTH_LONG).show();
        }
        metrs = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrs);

        Log.e("===metrs=====" + metrs.density, "====" + metrs.densityDpi);
    }


    @OnClick(R.id.second_btn)
    public void download() {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        String download_addr = secondBean.getApp().getDownload_addr();
        Log.e("vvvvvvvvvvvvvv","mmmmmmm"+download_addr);
        intent.setData(Uri.parse(download_addr));
        startActivity(intent);
    }


    public void showImg(List<SecondBean.ImgBean> imgs) {
        for (int i = 0; i < imgs.size(); i++) {
            ImageView img = new ImageView(this);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setPadding(5, 0, 5, 0);
            img.setLayoutParams(new LinearLayout.LayoutParams((int) (200 * metrs.density), (int) (250 * metrs.density)));
            Picasso.with(this).load(Contast.BASE + imgs.get(i).getAddress()).into(img);
            contener.addView(img);
        }

    }


    @OnClick(R.id.second_toolbar_back)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void DataBack(String json) {
        secondBean = Parser.JsonToObj(json, SecondBean.class);
        Log.e("qeqwer", "======" + json);
        handler.obtainMessage(1).sendToTarget();
    }
}
