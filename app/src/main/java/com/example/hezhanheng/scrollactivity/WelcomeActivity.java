package com.example.hezhanheng.scrollactivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hezhanheng.scrollactivity.views.CountDownProgressView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private final long SPLASH_LENGTH = 5000;
    private CountDownProgressView countdownProgressView;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        countdownProgressView=(CountDownProgressView)findViewById(R.id.countdownProgressView);
        countdownProgressView.setOnClickListener(this);
        countdownProgressView.start();
        handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_LENGTH);//2秒后跳转至应用主界面MainActivity
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.countdownProgressView:
                countdownProgressView.stop();
//                延时跳转
                new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        finish();
                        return false;
                    }
                }).sendEmptyMessageDelayed(0,1000);
                break;
        }

    }
}