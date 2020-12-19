package com.example.day_04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.day_04.view.fragment.AFragment;
import com.example.day_04.view.fragment.BFragment;
import com.example.day_04.view.fragment.CFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tablayout_home;
    private ViewPager viewPager_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        tablayout_home = (TabLayout) findViewById(R.id.tablayout_home);
        viewPager_home = (ViewPager) findViewById(R.id.viewPager_home);


        final List<Fragment> fragments = new ArrayList<>();

        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());


        viewPager_home.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        tablayout_home.setupWithViewPager(viewPager_home);

        tablayout_home.getTabAt(0).setText("经验榜");
        tablayout_home.getTabAt(1).setText("土豪榜");
        tablayout_home.getTabAt(2).setText("签到榜");

    }
}
