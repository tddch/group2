package com.example.night1228;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.banner_head)
    Banner bannerHead;
    @BindView(R.id.recyone)
    RecyclerView recyone;
    @BindView(R.id.rl_hai)
    RelativeLayout rlHai;
    @BindView(R.id.recytwo)
    RecyclerView recytwo;
    @BindView(R.id.nnnn)
    ImageView nnnn;
    private AdapterOne adapterOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.a);
        integers.add(R.drawable.b);
        integers.add(R.drawable.lisa);
        integers.add(R.drawable.mei);
        bannerHead.setImages(integers).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(MainActivity2.this).load(path).into(imageView);
            }
        }).start();
        recyone.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recytwo.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapterOne = new AdapterOne(this, integers);
        recyone.setAdapter(adapterOne);
        adapterOne.notifyDataSetChanged();

        recytwo.setAdapter(adapterOne);
        nnnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(  new Intent(MainActivity2.this,MainActivity.class));
            }
        });

    }
}