package com.example.chuanzkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager mVp;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.guide_page_one);
        integers.add(R.drawable.guide_page_two);
        integers.add(R.drawable.guide_page_three);
        Vpadapter vpadapter = new Vpadapter(this, integers);
        mVp.setAdapter(vpadapter);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    mBtn.setVisibility(View.GONE);
                }else if (position==1){
                    mBtn.setVisibility(View.GONE);
                }else if (position==2){
                    mBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                // TODO 21/01/02
                startActivity(new Intent(MainActivity.this,SlapshActivity.class));
                break;
            default:
                break;
        }
    }
}