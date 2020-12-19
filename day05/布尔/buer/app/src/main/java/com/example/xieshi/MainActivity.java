package com.example.xieshi;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.xieshi.fragment.FourFragment;
import com.example.xieshi.fragment.OneFragment;
import com.example.xieshi.fragment.ThreeFragment;
import com.example.xieshi.fragment.TwoFragment;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Integer> draw = new ArrayList<>();
    private Banner mBanner;

    private RecyclerView mRecycler;
    private TextView mTv;
    private TabLayout mTab;
    private ViewPager mVp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBanner = (Banner) findViewById(R.id.banner);
        draw.add(R.drawable.a);
        draw.add(R.drawable.b);
        draw.add(R.drawable.c);
        draw.add(R.drawable.d);
        draw.add(R.drawable.f);
        draw.add(R.drawable.g);
        mBanner.setImages(draw).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
        mTv = (TextView) findViewById(R.id.tv);
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
        mTab.setupWithViewPager(mVp);
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mTab.getTabAt(0).setText("首页");
        mTab.getTabAt(1).setText("诗歌");
        mTab.getTabAt(2).setText("作品");
        mTab.getTabAt(3).setText("我的");
    }

}