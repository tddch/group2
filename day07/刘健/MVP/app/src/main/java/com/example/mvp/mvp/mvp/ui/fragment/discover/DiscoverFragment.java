package com.example.mvp.mvp.mvp.ui.fragment.discover;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mvp.R;
import com.example.mvp.mvp.base.BaseFragment;
import com.example.mvp.mvp.base.BasePresenter;
import com.example.mvp.mvp.callback.RxObserverCallBack;
import com.example.mvp.mvp.mvp.model.RxOpretorImpl;
import com.example.mvp.mvp.mvp.model.bean.DiscoverTabBean;
import com.example.mvp.mvp.mvp.model.bean.DiscoverTopicBean;
import com.example.mvp.mvp.mvp.model.constants.ApiConstants;
import com.example.mvp.mvp.mvp.presenter.DiscoverPresenter;
import com.example.mvp.mvp.mvp.ui.fragment.discover.adapter.TopicRvAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.ResponseBody;


public class DiscoverFragment extends BaseFragment {


    @BindView(R.id.iv_paozi)
    ImageView ivPaozi;
    @BindView(R.id.iv_shetuan)
    ImageView ivShetuan;
    @BindView(R.id.iv_paihang)
    ImageView ivPaihang;
    @BindView(R.id.tv_paozi)
    TextView tvPaozi;
    @BindView(R.id.tv_shetuan)
    TextView tvShetuan;
    @BindView(R.id.tv_paihang)
    TextView tvPaihang;
    @BindView(R.id.con_img)
    ConstraintLayout conImg;
    @BindView(R.id.rv_topic)
    RecyclerView rvTopic;
    @BindView(R.id.tab_discover)
    TabLayout tabDiscover;
    @BindView(R.id.vp_discover)
    ViewPager vpDiscover;
    private ArrayList<DiscoverTopicBean.DataBean> topicList;
    private TopicRvAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabTitle;
    @Override
    protected BasePresenter createPresenter() {
        return new DiscoverPresenter(this);
    }
    RxOpretorImpl mi;
    @Override
    protected void init() {

        rvTopic.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        topicList = new ArrayList<>();
        adapter = new TopicRvAdapter(topicList, getActivity());
        rvTopic.setAdapter(adapter);


    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle.get(position);
        }
    }

    @Override
    protected void initData() {
        getmPresenter().start();
    }

    @Override
    public void onSuccess(Object o) {
        String str = (String) o;
        DiscoverTopicBean discoverTopicBean = new Gson().fromJson(str, DiscoverTopicBean.class);
        topicList.addAll(discoverTopicBean.getData());
        adapter.notifyDataSetChanged();
        Log.e("TAG", "onSuccess: " + str);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
    }
}