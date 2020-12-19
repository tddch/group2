package com.example.day03_project.ui.sofa.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SofaViewAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private String[] strings;

    public SofaViewAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> list, String[] strings) {
        super(fm);
        this.list = list;
        this.strings = strings;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}