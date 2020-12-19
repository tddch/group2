package com.example.day03_project.ui;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.day03_project.R;
import com.example.day03_project.ui.discover.DiscoverFragment;
import com.example.day03_project.ui.home.HomeFragment;
import com.example.day03_project.ui.my.MyFragment;
import com.example.day03_project.ui.sofa.SofaFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tab)
    TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        HomeFragment homeFragment = new HomeFragment();
        MyFragment myFragment = new MyFragment();
        SofaFragment sofaFragment = new SofaFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();


        transaction.add(R.id.fl,homeFragment)
                .add(R.id.fl,sofaFragment)
                .add(R.id.fl,discoverFragment)
                .add(R.id.fl,myFragment)
                .show(homeFragment)
                .hide(myFragment)
                .hide(sofaFragment)
                .hide(discoverFragment)
                .commit();

        tab.addTab(tab.newTab().setText("首页"));
        tab.addTab(tab.newTab().setText("沙发"));
        tab.addTab(tab.newTab().setText("推荐"));
        tab.addTab(tab.newTab().setText("我的"));


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragmentManager.beginTransaction()
                                .show(homeFragment)
                                .hide(myFragment)
                                .hide(sofaFragment)
                                .hide(discoverFragment)
                                .commit();
                        break;
                    case 1:
                        fragmentManager.beginTransaction()
                                .show(sofaFragment)
                                .hide(myFragment)
                                .hide(homeFragment)
                                .hide(discoverFragment)
                                .commit();
                        break;
                    case 2:
                        fragmentManager.beginTransaction()
                                .show(discoverFragment)
                                .hide(myFragment)
                                .hide(sofaFragment)
                                .hide(homeFragment)
                                .commit();
                        break;
                    case 3:
                        fragmentManager.beginTransaction()
                                .show(myFragment)
                                .hide(homeFragment)
                                .hide(sofaFragment)
                                .hide(discoverFragment)
                                .commit();
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