package com.example.day04_2;

import android.os.BadParcelableException;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day04_2.adapter.FragmentVpAdapter;
import com.example.day04_2.fragment.BlankFragment;
import com.example.day04_2.fragment.CallFragment;
import com.example.day04_2.fragment.HomeFragment;
import com.example.day04_2.fragment.RecallFragment;
import com.example.day04_2.fragment.ShowFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CallFragment());
        fragments.add(new HomeFragment());
        fragments.add(new RecallFragment());
        fragments.add(new ShowFragment());
        fragments.add(new BlankFragment());
        ArrayList<String> strings = new ArrayList<>();
        strings.add("首页");
        strings.add("发现");
        strings.add(" ");
        strings.add("商城");
        strings.add("我的");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.home1);
        list.add(R.drawable.home2);
        list.add(R.drawable.home5);
        list.add(R.drawable.home3);
        list.add(R.drawable.home4);
        FragmentVpAdapter fragmentVpAdapter = new FragmentVpAdapter(getSupportFragmentManager(), fragments, strings);
        vp.setAdapter(fragmentVpAdapter);
        tab.setupWithViewPager(vp);
        for (int i = 0; i < fragments.size(); i++) {
            tab.getTabAt(i).setIcon(list.get(i));
        }
    }
}