package com.yaokun.giftspirit.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yaokun.giftspirit.Parser.Parser;
import com.yaokun.giftspirit.R;
import com.yaokun.giftspirit.bean.GiftDetailBean;
import com.yaokun.giftspirit.http.DataBack;
import com.yaokun.giftspirit.http.HttpUtils;
import com.yaokun.giftspirit.inteface.Contast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetialActivity extends AppCompatActivity implements DataBack, View.OnClickListener {


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                initView();
            }

        }
    };

    GiftDetailBean giftDetailBean;
    @BindView(R.id.detail_toolbar_titel)
    TextView title;
    @BindView(R.id.detail_user_icon)
    CircleImageView icon;
    @BindView(R.id.detail_time_to)
    TextView time;
    @BindView(R.id.detail_number)
    TextView number;
    @BindView(R.id.detail_explains)
    TextView shuoming;
    @BindView(R.id.detail_descs)
    TextView fangshi;
    @BindView(R.id.detail_btn)
    Button btn;
    @BindView(R.id.detail_bottom_right)
    ImageView btom_right;

    public void initView(){
        title.setText(giftDetailBean.getInfo().getGname()+"-"+giftDetailBean.getInfo().getGiftname());
        Picasso.with(this).load(Contast.BASE+giftDetailBean.getInfo().getIconurl()).into(icon);
        time.setText(giftDetailBean.getInfo().getOvertime());
        number.setText(String.valueOf(giftDetailBean.getInfo().getNumber()));
        shuoming.setText(giftDetailBean.getInfo().getExplains());
        fangshi.setText(giftDetailBean.getInfo().getDescs());
        int number2 = giftDetailBean.getInfo().getNumber();
        if(number2<=0){
            btn.setText("暂无领取");
            btn.setBackground(getResources().getDrawable(R.drawable.btn_gb_gray));
            btn.setEnabled(false);
        }
    }


    @OnClick(R.id.detail_toolbar_back)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        HttpUtils.GetData(Contast.GIFT_DETAIL+id,this);
        btn.setOnClickListener(this);
    }


    @OnClick(R.id.detial_toolbar_share)
    public void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享："+giftDetailBean.getInfo().getGname());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(giftDetailBean.getInfo().getTurl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText("");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(Contast.BASE+giftDetailBean.getInfo().getIconurl());
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
        Log.e("DetialActivity","DetialActivity"+json);
        giftDetailBean= Parser.JsonToObj(json,GiftDetailBean.class);
        handler.obtainMessage(0).sendToTarget();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,"未登录",Toast.LENGTH_LONG).show();
    }
}
