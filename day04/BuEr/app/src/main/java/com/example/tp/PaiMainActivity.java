package com.example.tp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tp.fragment.DengFragment;
import com.example.tp.fragment.HaoFragment;
import com.example.tp.fragment.QianFragment;
import com.example.tp.fragment.TuFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PaiMainActivity extends AppCompatActivity {

    private ImageView mImage1;
    private TabLayout mTablayout;
    private ViewPager mViewpager;
    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_main);
        initView();
    }

    private void initView() {
        mImage1 = (ImageView) findViewById(R.id.image1);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);

        fragments = new ArrayList<>();

        fragments.add(new HaoFragment());
        fragments.add(new DengFragment());
        fragments.add(new QianFragment());
        mTablayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        mTablayout.getTabAt(0).setText("土豪榜");
        mTablayout.getTabAt(1).setText("等级榜");
        mTablayout.getTabAt(2).setText("签到榜");

        mImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}