package com.example.mvp.mvp.mvp.ui.actuvity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.mvp.R;
import com.example.mvp.mvp.app.App;
import com.example.mvp.mvp.base.BasePresenter;
import com.example.mvp.mvp.base.IBaseActivity;
import com.example.mvp.mvp.engine.adapter.HomeActVpAdapter;
import com.example.mvp.mvp.manager.ContainManager;
import com.example.mvp.mvp.manager.ThreadPoolManager;
import com.example.mvp.mvp.mvp.ui.fragment.HomeFragment;
import com.example.mvp.mvp.mvp.ui.fragment.discover.DiscoverFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends IBaseActivity {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager mHomeActVp;
    private boolean mIsExit;
    private FragmentManager mManager;
    private int[] tabName = {R.string.home_tab_title, R.string.collect_tab_title};


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void init() {
        mManager = getSupportFragmentManager();
        HomeActVpAdapter homeActVpAdapter = new HomeActVpAdapter(mManager, getFragments(), tabName);
        mHomeActVp.setAdapter(homeActVpAdapter);
        tab.setupWithViewPager(mHomeActVp);

    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList();
        fragments.add(new HomeFragment());
        fragments.add(new DiscoverFragment());
        return fragments;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //TODO  2秒内连续点击两次 退出程序，  如果第二次点击超过2秒了，会Toast提示再按一次退出程序
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(App.context(), "再按一次退出程序", Toast.LENGTH_LONG).show();
                //2秒之后把变量值改成true
                ThreadPoolManager.
                        getThreadPool(ThreadPoolManager.SCHDULE_THREADPOOL).
                        executeTimerTask(new Runnable() {
                            @Override
                            public void run() {
                                mIsExit = false;
                            }
                        }, 2, TimeUnit.SECONDS);
            } else {
                ContainManager.getmManager().clearActivity();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}