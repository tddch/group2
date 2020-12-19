package com.example.day04_2.model;

import android.graphics.Paint;

import com.example.day04_2.ApiService;
import com.example.day04_2.bean.ReWenBean;
import com.example.day04_2.recall.ReCall;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {
    public void getData(final ReCall reCall){
        new Retrofit.Builder()
                .baseUrl(ApiService.BEAN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getReWenBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReWenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReWenBean value) {
                        if(reCall!=null){
                            reCall.showOK(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(reCall==null){
                            reCall.showNo(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
