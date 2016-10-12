package com.yaokun.giftspirit.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.adapter.Wed_Detail_Adapter;
import com.yaokun.giftspirit.bean.Wed_Detailbean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class Wed_Detail_Activity extends AppCompatActivity implements DataBack, AdapterView.OnItemClickListener {

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            gridView.setAdapter(new Wed_Detail_Adapter(Wed_Detail_Activity.this,wed_detailbean));
        }
    };


    @BindView(R.id.wed_detail_toolbar_share)
    ImageView share;
    @BindView(R.id.wed_detail_icon)
    ImageView icon;
    @BindView(R.id.wed_detail_time)
    TextView time;
    @BindView(R.id.wed_detail_daodu)
    TextView daodu;
    @BindView(R.id.wed_detail_gv)
    GridView gridView;

    Wed_Detailbean wed_detailbean;
    String descs;
    String iconurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wed_detail);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        descs=intent.getStringExtra("descs");
        iconurl=intent.getStringExtra("iconurl");
        String addtime=intent.getStringExtra("addtime");
        Picasso.with(this).load(Contast.BASE+iconurl).into(icon);
        daodu.setText("\u3000\u3000\u3000"+descs);
        time.setText(addtime);
        HttpUtils.GetData(Contast.WED_DETAIL+id,this);
        gridView.setOnItemClickListener(this);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    public void showShare() {
        Log.e("wed_detail_toolbshare","wed_detail_toolbar_share");
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享到：");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(descs);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(Contast.BASE+iconurl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


    @OnClick(R.id.wed_detail_toolbar_back)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void DataBack(String json) {
        Log.e("DE====","------------"+json);
        wed_detailbean=Parser.JsonToObj(json,Wed_Detailbean.class);
        handler.obtainMessage(1).sendToTarget();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String appid = wed_detailbean.getList().get(position).getAppid();
        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra("gid",appid);
        startActivity(intent);
    }
}
