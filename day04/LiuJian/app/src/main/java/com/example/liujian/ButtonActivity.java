package com.example.liujian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liujian.baidu.BaiduActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButtonActivity extends AppCompatActivity {

    @BindView(R.id.btn_tongpao)
    Button btnTongpao;
    @BindView(R.id.btn_baidu_path)
    Button btnBaiduPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_tongpao, R.id.btn_baidu_path})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tongpao:
                startActivity(new Intent(ButtonActivity.this,MainActivity.class));
                break;
            case R.id.btn_baidu_path:
                startActivity(new Intent(ButtonActivity.this, BaiduActivity.class));
                break;
        }
    }
}