package com.example.xmu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.xmu.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends Fragment {
    private TabLayout mTablayout;
    private ViewPager mViewpager;
    List<Fragment> fragments;
    String [] tabs ={"图片","视频","文本"};
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.two_item, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mTablayout = (TabLayout) itemView.findViewById(R.id.tablayout);
        mViewpager = (ViewPager) itemView.findViewById(R.id.viewpager);
        fragments=new ArrayList<>();
//        for (int i = 0; i < tabs.length; i++) {
//            mTablayout.addTab(mTablayout.newTab().setText(tabs[i]));
            fragments.add(new TuFragment());
            fragments.add(new ShiFragment());
            fragments.add(new WenFragment());
//    }

        //初始化ViewPager
        myAdapter = new MyAdapter(getChildFragmentManager());
        mViewpager.setAdapter(myAdapter);
        mTablayout.setupWithViewPager(mViewpager);
    }
    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //写出getPagertitle方法
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}
