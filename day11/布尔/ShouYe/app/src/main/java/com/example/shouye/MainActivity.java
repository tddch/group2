package com.example.shouye;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private Banner mBanner;
    private RecyclerView mRv;
    List<Integer> draw = new ArrayList<>();
    private GirlAdapter adapter;
    private ArrayList<Bean.ResultsBean> resultsBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        new Retrofit.Builder().baseUrl(ApiService.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .GirlData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        resultsBeans.addAll(bean.getResults());
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

    private void initView() {
        mBanner = (Banner) findViewById(R.id.banner);
        mRv = (RecyclerView) findViewById(R.id.rv);
        //getApplication(),RecyclerView.HORIZONTAL,false)

        mRv.setLayoutManager(new LinearLayoutManager(this));
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

        resultsBeans = new ArrayList<>();
        adapter = new GirlAdapter(this, resultsBeans);
        mRv.setAdapter(adapter);
    }
}