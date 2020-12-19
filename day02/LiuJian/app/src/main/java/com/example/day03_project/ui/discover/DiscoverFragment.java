package com.example.day03_project.ui.discover;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day03_project.R;
import com.example.day03_project.ui.sofa.SofaImgFragment;
import com.example.day03_project.ui.sofa.SofaTextFragment;
import com.example.day03_project.ui.sofa.SofaVideoFragment;
import com.example.day03_project.ui.sofa.adapter.SofaViewAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DiscoverFragment extends Fragment {


    private Unbinder bind;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    String[] tabName=new String[]{"关注","推荐"};
    private ArrayList<Fragment> fragments;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        bind = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        fragments = new ArrayList<>();
        fragments.add(new AttentionFragment());
        fragments.add(new RecommendFragment());

        vp.setAdapter(new SofaViewAdapter(getActivity().getSupportFragmentManager(),fragments,tabName));
//        vp.setCurrentItem(1);
        tab.setupWithViewPager(vp);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}