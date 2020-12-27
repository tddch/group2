package com.example.mvpbaaaaaaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpbaaaaaaa.base.BaseActivity;
import com.example.mvpbaaaaaaa.base.BasePresenter;
import com.example.mvpbaaaaaaa.utils.PoiSugSearchDemo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.btn_poi)
    Button btnPoi;
    @BindView(R.id.btn_xx)
    Button btnXx;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }




    @OnClick({R.id.btn_poi, R.id.btn_xx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_poi:
                startActivity(new Intent(MainActivity.this, PoiSugSearchDemo.class));
                break;
            case R.id.btn_xx:
                break;
        }
    }
}