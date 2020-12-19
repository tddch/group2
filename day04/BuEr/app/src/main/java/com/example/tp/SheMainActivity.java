package com.example.tp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp.adapter.AsscoAdapter;
import com.example.tp.bean.AsscoBean;
import com.example.tp.bean.DiscoverBean;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SheMainActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private ImageView mImg;
    private AsscoAdapter adapter;
    private ArrayList<AsscoBean.DataBean.ListBean> listBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_main);
        initData();
        initView();
    }

    private void initData() {
        new Retrofit.Builder().baseUrl(ApiService.sUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getAssoc()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AsscoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AsscoBean asscoBean) {
                        listBeans.addAll(asscoBean.getData().getList());
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
        mRv = (RecyclerView) findViewById(R.id.rv);
        mImg = (ImageView) findViewById(R.id.img);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        listBeans = new ArrayList<>();
        adapter = new AsscoAdapter(this, listBeans);
        mRv.setAdapter(adapter);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}