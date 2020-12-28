package com.example.jiguang.jiguang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jiguang.R;
import com.example.jiguang.mvp.ui.fragment.HomeFragment;
import com.example.jiguang.mvp.ui.fragment.MyFragment;
import com.example.jiguang.mvp.ui.fragment.StrategyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tab)
    TabLayout tab;
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "cn.jiguang.demo.jpush.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        JPushInterface.init(getApplicationContext());
        initView();
        registerMessageReceiver();

    }

    private void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public static int sequence = 1;

    @Override
    public void onClick(View v) {
        String alias = null;
        Set<String> tags = null;
        int action = -1;
        boolean isAliasAction = false;

        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = action;
        sequence++;
        if (isAliasAction) {
            tagAliasBean.alias = alias;
        } else {
            tagAliasBean.tags = tags;
        }
        tagAliasBean.isAliasAction = isAliasAction;
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);
    }


    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!DeviceUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    Log.e("TAG", showMsg.toString());
                }
            } catch (Exception e) {
            }
        }
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private void initView() {
        //获取appkey
        String appKey = DeviceUtil.getAppKey(getApplicationContext());
        Log.e("TAG", "appKey:" + appKey);
        //获取包名
        String packageName = getPackageName();
        Log.e("TAG", "PackageName:" + packageName);
        //获取deviceId
        String deviceId = DeviceUtil.getDeviceId(getApplicationContext());
        Log.e("TAG", "DeviceId:" + deviceId);

        final HomeFragment addFragment = new HomeFragment();
        final MyFragment myFragment = new MyFragment();
        final StrategyFragment storeFragment = new StrategyFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();

        final FragmentTransaction transaction = fragmentManager.beginTransaction();


        transaction.add(R.id.fl, addFragment)
                .add(R.id.fl, storeFragment)
                .add(R.id.fl, myFragment)
                .hide(myFragment)
                .hide(storeFragment)
                .show(addFragment)
                .commit();


        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.sy_selector));
        tab.addTab(tab.newTab().setText("发现").setIcon(R.drawable.fx_selector));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.my_selector));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragmentManager.beginTransaction()
                                .hide(myFragment)
                                .hide(storeFragment)
                                .show(addFragment)
                                .commit();
                        break;
                    case 1:
                        fragmentManager.beginTransaction()
                                .hide(myFragment)
                                .show(storeFragment)
                                .hide(addFragment)
                                .commit();
                        break;
                    case 2:
                        fragmentManager.beginTransaction()
                                .show(myFragment)
                                .hide(storeFragment)
                                .hide(addFragment)
                                .commit();
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

        tab.getTabAt(0).setText("首页")
                .setIcon(R.drawable.sy_selector);
        tab.getTabAt(1).setText("攻略")
                .setIcon(R.drawable.fx_selector);
        tab.getTabAt(2).setText("我的")
                .setIcon(R.drawable.my_selector);

    }
}
