package com.example.liujian.ui.discover.shetuan;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liujian.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheTuanActivity extends AppCompatActivity {

    @BindView(R.id.iv_shetian_finish)
    ImageView ivShetianFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_tuan);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_shetian_finish)
    public void onClick() {
        finish();
    }
}