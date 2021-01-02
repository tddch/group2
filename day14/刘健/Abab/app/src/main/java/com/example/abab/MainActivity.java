package com.example.abab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.abab.mvp.ui.HomeActivity;

import java.util.ArrayList;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv_item)
    TextView tvItem;
    private TextView te;
    private Timer timer;
    private boolean s;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("aa", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        boolean bleann = sharedPreferences.getBoolean("bleann", false);
        if (bleann){
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }

        initView();
    }

    private void initView() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img3);

        ViewAdapter adapter = new ViewAdapter(list, this);
        vp.setAdapter(adapter);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position  == 2) {
                    tvItem.setVisibility(View.VISIBLE);
                } else {
                    tvItem.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putBoolean("bleann",true).commit();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        });

    }
}