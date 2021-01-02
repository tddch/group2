package com.example.abab.mvp.ui;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.abab.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeTabActivity extends AppCompatActivity {

    @BindView(R.id.iv_item)
    ImageView ivItem;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_seek)
    ImageView ivSeek;
    @BindView(R.id.iv_mail)
    ImageView ivMail;
    @BindView(R.id.ll)
    ConstraintLayout ll;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tab)
    TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tab);
        ButterKnife.bind(this);
    }
}