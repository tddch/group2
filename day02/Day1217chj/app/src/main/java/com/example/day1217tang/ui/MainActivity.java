package com.example.day1217tang.ui;

import android.util.Log;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.day1217tang.R;
import com.example.day1217tang.base.BaseActivity;

import com.example.day1217tang.fragments.BlankFragment;
import com.example.day1217tang.fragments.BlankFragment2;
import com.example.day1217tang.interfaces.INew;
import com.example.day1217tang.model.bean.FindMain;
import com.example.day1217tang.model.bean.SofoMp4;
import com.example.day1217tang.model.bean.SofoPicture;
import com.example.day1217tang.model.bean.SofoText;
import com.example.day1217tang.presenter.PresenterNew;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class MainActivity extends BaseActivity<PresenterNew> implements INew.View {


    @BindView(R.id.tab_main)
    TabLayout tabMain;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    private BlankFragment blankFragment;
    private BlankFragment2 blankFragment2;
    private FragmentManager supportFragmentManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;

    }

    @Override
    protected void initView() {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        blankFragment = new BlankFragment();
        blankFragment2 = new BlankFragment2();
        fragmentTransaction.add(R.id.rl_main,blankFragment)
                .add(R.id.rl_main, blankFragment2)
                .show(blankFragment)
                .hide(blankFragment2).commit();
        tabMain.addTab(tabMain.newTab().setText("沙发"));
        tabMain.addTab(tabMain.newTab().setText("发现"));
        tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()){
                    case 0:

                        FragmentTransaction fragmentTransaction1 = supportFragmentManager.beginTransaction();
                        fragmentTransaction1.show(blankFragment).hide(blankFragment2).commit();

                        break;
                    case 1:
                        FragmentTransaction fragmentTransaction2 = supportFragmentManager.beginTransaction();
                        fragmentTransaction2.show(blankFragment2).hide(blankFragment).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected PresenterNew createPresenter() {
        return new PresenterNew(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void getSofoPicture(SofoPicture sofoPicture) {

    }

    @Override
    public void getSofoMp4(SofoMp4 sofoMp4) {

    }

    @Override
    public void getSofoText(SofoText sofoText) {

    }

    @Override
    public void getFindMain(FindMain findMain) {

    }
}