package com.example.liujian;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.liujian.ui.add.AddFragment;
import com.example.liujian.ui.discover.DiscoverFragment;
import com.example.liujian.ui.my.MyFragment;
import com.example.liujian.ui.recommend.RecommendFragment;
import com.example.liujian.ui.store.StoreFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tab)
    TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        AddFragment addFragment = new AddFragment();
        MyFragment myFragment = new MyFragment();
        StoreFragment storeFragment = new StoreFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        RecommendFragment recommendFragment = new RecommendFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();


        transaction.add(R.id.fl, addFragment)
                .add(R.id.fl, storeFragment)
                .add(R.id.fl, discoverFragment)
                .add(R.id.fl, myFragment)
                .add(R.id.fl, recommendFragment)
                .show(recommendFragment)
                .hide(myFragment)
                .hide(storeFragment)
                .hide(addFragment)
                .hide(discoverFragment)
                .commit();


        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.sy_selector));
        tab.addTab(tab.newTab().setText("发现").setIcon(R.drawable.fx_selector));
        tab.addTab(tab.newTab().setIcon(R.drawable.x));
        tab.addTab(tab.newTab().setText("商城").setIcon(R.drawable.sc_selector));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.my_selector));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragmentManager.beginTransaction()
                                .show(recommendFragment)
                                .hide(myFragment)
                                .hide(storeFragment)
                                .hide(addFragment)
                                .hide(discoverFragment)
                                .commit();
                        break;
                    case 1:
                        fragmentManager.beginTransaction()
                                .show(discoverFragment)
                                .hide(myFragment)
                                .hide(storeFragment)
                                .hide(addFragment)
                                .hide(recommendFragment)
                                .commit();
                        break;
                    case 2:

                        break;
                    case 3:
                        fragmentManager.beginTransaction()
                                .show(storeFragment)
                                .hide(myFragment)
                                .hide(discoverFragment)
                                .hide(addFragment)
                                .hide(recommendFragment)
                                .commit();
                        break;
                    case 4:
                        fragmentManager.beginTransaction()
                                .show(myFragment)
                                .hide(storeFragment)
                                .hide(discoverFragment)
                                .hide(addFragment)
                                .hide(recommendFragment)
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

        tab.getTabAt(0).setText("首页")
                .setIcon(R.drawable.sy_selector);
        tab.getTabAt(1).setText("发现")
                .setIcon(R.drawable.fx_selector);
        tab.getTabAt(2)
//                .setText( "发布" )
                .setIcon(R.drawable.x);
        tab.getTabAt(3).setText("商城")
                .setIcon(R.drawable.sc_selector);
        tab.getTabAt(4).setText("我的")
                .setIcon(R.drawable.my_selector);

    }
}