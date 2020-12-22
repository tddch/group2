package com.example.mymvp.mvp.model;

import com.example.mymvp.mvp.model.constants.ApiConstans;
import com.example.mymvp.mvp.model.api.ApiService;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
        // TODO: 2020/12/22 向外界提供对象的注解
public class OkManager {
    @Singleton // TODO: 2020/12/22 单例
     @Provides
   public OkHttpClient.Builder proBuilder(){
        return new OkHttpClient.Builder();
    }
    @Singleton
    @Provides
    public  OkHttpClient proOkHttpClient(){
        return proBuilder().build();
    }

    @Singleton
    @Provides
    public Retrofit.Builder proRetroBuilder(){
        return new Retrofit.Builder();
    }
    @Singleton
    @Provides
    public Retrofit proRetrofit(){
        return proRetroBuilder()
                .baseUrl(ApiConstans.TONG_PAO)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    @Singleton
    @Provides
    public ApiService proApiService(){
        return proRetrofit().create(ApiService.class);
    }


}
