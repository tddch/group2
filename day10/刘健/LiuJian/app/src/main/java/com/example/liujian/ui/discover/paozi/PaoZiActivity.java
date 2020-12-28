package com.example.liujian.ui.discover.paozi;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liujian.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaoZiActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pao_zi);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.iv_finish)
    public void onClick() {
        finish();
    }
}