package com.example.liujian.ui.discover.paihang;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.liujian.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaiHangActivity extends AppCompatActivity {

    @BindView(R.id.iv_paihang_finish)
    ImageView ivPaihangFinish;
    @BindView(R.id.money_tb)
    TabLayout moneyTb;
    @BindView(R.id.money_vp)
    ViewPager moneyVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_hang);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MoneyFragment());
        fragments.add(new LevelFragment());
        fragments.add(new SignFragment());
        moneyVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        moneyTb.setupWithViewPager(moneyVp);
        moneyTb.getTabAt(0).setText("土豪榜");
        moneyTb.getTabAt(1).setText("等级榜");
        moneyTb.getTabAt(2).setText("签到榜");
    }

    @OnClick(R.id.iv_paihang_finish)
    public void onClick() {
        finish();
    }
}