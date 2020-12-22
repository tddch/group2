package com.example.mymvp.mvp.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mymvp.R;
import com.example.mymvp.app.Mapp;
import com.example.mymvp.base.BaseActivity;
import com.example.mymvp.base.BasePresenter;
import com.example.mymvp.base.interfaces.IBaseView;
import com.example.mymvp.manager.ContainManager;
import com.example.mymvp.manager.ThreadPoolManage;
import com.example.mymvp.mvp.ui.fragments.AddFragment;
import com.example.mymvp.mvp.ui.fragments.FindFragment;
import com.example.mymvp.mvp.ui.fragments.HomeFragment;
import com.example.mymvp.mvp.ui.fragments.MySelfFragment;
import com.example.mymvp.mvp.ui.fragments.ShopFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IBaseView {

    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    private boolean mIsExit;
    private int[] mTabs = {R.string.main_home_tit, R.string.main_find_tit, R.string.main_add_tit, R.string.main_shop_tit, R.string.main_mySelf_tit};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        HomeFragment homeFragment = new HomeFragment();
        FindFragment findFragment = new FindFragment();
        ShopFragment shopFragment = new ShopFragment();

        AddFragment addFragment = new AddFragment();
        MySelfFragment mySelfFragment = new MySelfFragment();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(findFragment);
        fragments.add(addFragment);
        fragments.add(shopFragment);
        fragments.add(mySelfFragment);
        vpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabMain.setupWithViewPager(vpMain);
        for (int i = 0; i < mTabs.length; i++) {
            tabMain.getTabAt(i).setText(Mapp.getStr(mTabs[i]));
        }
        tabMain.getTabAt(0).setIcon(R.drawable.home);
        tabMain.getTabAt(1).setIcon(R.drawable.find);
        tabMain.getTabAt(2).setIcon(R.drawable.add);
        tabMain.getTabAt(3).setIcon(R.drawable.shop);
        tabMain.getTabAt(4).setIcon(R.drawable.myself);

    }

    @Override
    protected BasePresenter createPresenter() {

        return null;

    }

    @Override
    protected void initData() {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // TODO: 2020/12/21 如果点击Toast 状态切换ture
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // TODO: 2020/12/21 两秒内点击进入变状态false退出计时进入else
                ThreadPoolManage.getThreadPool(ThreadPoolManage.SCHDULE_THREADPOOL)
                        .executeTimerTask(new Runnable() {
                            @Override
                            public void run() {
                                mIsExit = false;
                            }
                        }, 2, TimeUnit.SECONDS);

            } else {
                // TODO: 2020/12/21 退出界面
                ContainManager.getmManager().clearActivity();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);

    }



}