package com.example.day12;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frame;
    private TabLayout tab;
    private TextView yev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        frame = (FrameLayout) findViewById(R.id.frame);
        tab = (TabLayout) findViewById(R.id.tab);
        yev = (TextView) findViewById(R.id.yev);
        final HomeFragment homeFragment = new HomeFragment();
        final BlankFragment blankFragment = new BlankFragment();
        final BlankFragment2 blankFragment2 = new BlankFragment2();
        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.m).setTag(0));
        tab.addTab(tab.newTab().setText("攻略").setIcon(R.drawable.l).setTag(1));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.n).setTag(2));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, homeFragment)
                                .commit();
                        yev.setText(tab.getText());
                        break;
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, blankFragment)
                                .commit();
                        yev.setText(tab.getText());
                        break;
                    case 2:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, blankFragment2)
                                .commit();
                        yev.setText(tab.getText());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}