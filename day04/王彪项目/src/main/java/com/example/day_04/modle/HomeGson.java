package com.example.day_04.modle;

import com.example.day_04.back.HomeBack;
import com.example.day_04.modle.bean.JingYanBean;
import com.example.day_04.modle.bean.QianDaoBean;
import com.example.day_04.modle.bean.RecyBean;
import com.example.day_04.modle.bean.TuHaoBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeGson {
    public void start(final HomeBack back) {
        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecyBean value) {
                        if (back != null) {
                            back.showdui(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (back != null) {
                            back.showcuo(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getJing()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JingYanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JingYanBean value) {
                        if (back != null) {
                            back.showdui(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (back != null) {
                            back.showcuo(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });




        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getQian()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QianDaoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QianDaoBean value) {
                        if (back != null) {
                            back.showdui(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (back != null) {
                            back.showcuo(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });



        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getTu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TuHaoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TuHaoBean value) {
                        if (back != null) {
                            back.showdui(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (back != null) {
                            back.showcuo(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
