package com.example.tp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tp.ApiService;
import com.example.tp.PaiMainActivity;
import com.example.tp.R;
import com.example.tp.SheMainActivity;
import com.example.tp.adapter.DiscoverAdapter;
import com.example.tp.bean.DiscoverBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwoFragment extends Fragment {
    private RecyclerView mRv;
    private TabLayout mTabb;
    private ViewPager mVp;
    List<Fragment> fragments;
    String[] tabs = {"热点", "妆造", "图鉴", "百科"};
    private MyAdapter myAdapter;
    private DiscoverAdapter adapter;
    private ArrayList<DiscoverBean.DataBean> dataBeans;
    private ImageView mTwo;
    private ImageView mThree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.faxian, null);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        new Retrofit.Builder().baseUrl(ApiService.dUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .reData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DiscoverBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DiscoverBean discoverBean) {
                        dataBeans.addAll(discoverBean.getData());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(@NonNull final View itemView) {
        mRv = (RecyclerView) itemView.findViewById(R.id.rv);
        mTabb = (TabLayout) itemView.findViewById(R.id.tabb);
        mVp = (ViewPager) itemView.findViewById(R.id.vp);
        mTwo = (ImageView) itemView.findViewById(R.id.two);
        mThree = (ImageView) itemView.findViewById(R.id.three);
        mRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        dataBeans = new ArrayList<>();
        adapter = new DiscoverAdapter(getContext(), dataBeans);
        mRv.setAdapter(adapter);
        mTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),SheMainActivity.class));
            }
        });
        mThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PaiMainActivity.class));
            }
        });


        fragments = new ArrayList<>();
        for (int i = 0; i < tabs.length; i++) {
            mTabb.addTab(mTabb.newTab().setText(tabs[i]));
            fragments.add(new TuFragment());
        }

        //初始化ViewPager
        myAdapter = new MyAdapter(getChildFragmentManager());
        mVp.setAdapter(myAdapter);
        mTabb.setupWithViewPager(mVp);

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
