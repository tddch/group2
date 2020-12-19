package com.example.day03_project.ui.sofa;

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

import com.example.day03_project.R;
import com.example.day03_project.ui.sofa.adapter.SofaViewAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SofaFragment extends Fragment {


    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private Unbinder unbinder;
    String[] tabName=new String[]{"图片","视频","文本"};
    private ArrayList<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sofa, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        fragments = new ArrayList<>();
        fragments.add(new SofaImgFragment());
        fragments.add(new SofaVideoFragment());
        fragments.add(new SofaTextFragment());

        vp.setAdapter(new SofaViewAdapter(getActivity().getSupportFragmentManager(),fragments,tabName));

        tab.setupWithViewPager(vp);

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}