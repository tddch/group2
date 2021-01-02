package com.example.abab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    int i = 2;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                i--;

                if (i == 0) {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }
    };
    @BindView(R.id.iv_item)
    ImageView ivItem;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Glide.with(ivItem).load(R.mipmap.ic_launcher_round).into(ivItem);


//getSharedPreference是取值    edit是赋值

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(0);

            }
        }, 0, 1000);

    }
}