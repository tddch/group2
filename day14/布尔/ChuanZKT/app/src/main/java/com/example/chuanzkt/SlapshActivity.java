package com.example.chuanzkt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.chuanzkt.fragment.DescFragment;
import com.example.chuanzkt.fragment.HomeFragment;
import com.example.chuanzkt.fragment.MyFragment;
import com.example.chuanzkt.fragment.XueFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SlapshActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slapsh);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DescFragment());
        fragments.add(new XueFragment());
        fragments.add(new MyFragment());
        mTab.setupWithViewPager(mVp);
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mTab.getTabAt(0).setText("首页").setIcon(R.drawable.image);
        mTab.getTabAt(1).setText("课程").setIcon(R.drawable.image1);
        mTab.getTabAt(2).setText("学习").setIcon(R.drawable.fenzu24);
        mTab.getTabAt(3).setText("我的").setIcon(R.drawable.image2);
    }
}