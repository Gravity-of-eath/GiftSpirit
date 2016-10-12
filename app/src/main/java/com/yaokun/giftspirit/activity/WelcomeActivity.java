package com.yaokun.giftspirit.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yaokun.giftspirit.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {
    TextView textView;
    int time=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textView= (TextView) findViewById(R.id.wc_time);
        final Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(time+"S");
                        time--;
                        if(time<=-1){
                            timer.cancel();
                            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                            startActivity(intent);
                            WelcomeActivity.this.finish();
                        }
                    }
                });

            }
        };
        timer.schedule(task,0,1000);



    }
}
