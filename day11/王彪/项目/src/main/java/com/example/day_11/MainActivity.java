package com.example.day_11;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.day_11.fragment.CenterFragment;
import com.example.day_11.fragment.LeftFragment;
import com.example.day_11.fragment.RightFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabalyout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        final List<Fragment> fragments = new ArrayList<>();

        fragments.add(new LeftFragment());
        fragments.add(new RightFragment());
        fragments.add(new CenterFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        tabalyout.setupWithViewPager(viewPager);

        tabalyout.getTabAt(0).setText("首页").setIcon(R.drawable.m);
        tabalyout.getTabAt(1).setText("攻略").setIcon(R.drawable.n);
        tabalyout.getTabAt(2).setText("我的").setIcon(R.drawable.l);

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabalyout = (TabLayout) findViewById(R.id.tabalyout);
    }
}
