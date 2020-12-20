package com.example.day_04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.day_04.view.fragment.LeftFragment;
import com.example.day_04.view.fragment.RightFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager_main;
    private TabLayout tablayout_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager_main = (ViewPager) findViewById(R.id.viewPager_main);
        tablayout_main = (TabLayout) findViewById(R.id.tablayout_main);


        final List<Fragment> fragments = new ArrayList<>();


        fragments.add(new RightFragment());
        fragments.add(new LeftFragment());
        fragments.add(new RightFragment());
        fragments.add(new RightFragment());

        viewPager_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        tablayout_main.setupWithViewPager(viewPager_main);

        tablayout_main.getTabAt(0).setText("首页");
        tablayout_main.getTabAt(1).setText("发现");
        tablayout_main.getTabAt(2).setText("商城");
        tablayout_main.getTabAt(3).setText("我的");

    }
}
