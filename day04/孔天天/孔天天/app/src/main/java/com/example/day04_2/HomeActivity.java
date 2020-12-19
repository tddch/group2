package com.example.day04_2;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.day04_2.adapter.FragmentVpAdapter;
import com.example.day04_2.fragment1.BlankFragment1;
import com.example.day04_2.fragment1.BlankFragment2;
import com.example.day04_2.fragment1.BlankFragment3;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private TextView tv_toole;
    private Toolbar toolbar;
    private ViewPager vp;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        tv_toole = (TextView) findViewById(R.id.tv_toole);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BlankFragment1());
        fragments.add(new BlankFragment2());
        fragments.add(new BlankFragment3());
        ArrayList<String> strings = new ArrayList<>();
        strings.add("经验榜");
        strings.add("土豪榜");
        strings.add("签到榜");
        FragmentVpAdapter fragmentVpAdapter = new FragmentVpAdapter(getSupportFragmentManager(),fragments, strings);
        vp.setAdapter(fragmentVpAdapter);
        tab.setupWithViewPager(vp);
    }
}