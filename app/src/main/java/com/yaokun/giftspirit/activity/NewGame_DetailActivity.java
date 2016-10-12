package com.yaokun.giftspirit.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.adapter.NewGame_DetailAdapter;
import com.yaokun.giftspirit.bean.NewGame_DetailBean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class NewGame_DetailActivity extends AppCompatActivity implements DataBack {


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.setBean(bean);
            adapter.notifyDataSetChanged();
        }
    };

    @BindView(R.id.newgame_detail_titel_titel)
    TextView titel;
    @BindView(R.id.newgame_detail_icon)
    ImageView icon;
    @BindView(R.id.newgame_detail_attoricon)
    ImageView actoricon;
    @BindView(R.id.newgame_detail_shuoming)
    TextView shuoming;
    @BindView(R.id.newgame_detail_lv)
    GridView gridView;

    Intent intent;
    NewGame_DetailBean bean;
    private NewGame_DetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game__detail);
        ButterKnife.bind(this);
        intent=getIntent();
        Log.e("NewGame_DetailActivity","NewGame_DetailActivity===  "+Contast.NEWGAME_DETAIL+intent.getStringExtra("id"));
        Picasso.with(this).load(Contast.BASE+intent.getStringExtra("icon")).into(icon);
        Picasso.with(this).load(Contast.BASE+"/"+intent.getStringExtra("actoricon")).into(actoricon);
        shuoming.setText(intent.getStringExtra("descs"));
        titel.setText(intent.getStringExtra("titel"));
        String id = intent.getStringExtra("id");
        if(!TextUtils.isEmpty(id)) {
            HttpUtils.GetData(Contast.NEWGAME_DETAIL + id, this);
        }else {
            Toast.makeText(this,"暂无APP信息",Toast.LENGTH_LONG).show();
        }
        adapter=new NewGame_DetailAdapter(bean,this);
        gridView.setAdapter(adapter);
    }

    @OnClick(R.id.newgame_detail_titel_back)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.newgame_detail_titel_share)
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
        oks.setText("分享："+intent.getStringExtra("titel"));
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(Contast.BASE+intent.getStringExtra("icon"));
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

    @Override
    public void DataBack(String json) {
      bean=  Parser.JsonToObj(json, NewGame_DetailBean.class);
        handler.obtainMessage(1).sendToTarget();
    }
}
