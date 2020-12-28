package com.example.liujian.net;


import com.example.liujian.api.TongpaoApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class HttpManager {
    private static HttpManager instance;

    public static HttpManager getInstance() {
        if(instance==null){
            synchronized (HttpManager.class){
                if(instance==null){
                    instance=new HttpManager();
                }
            }
        }
        return instance;
    }

    private TongpaoApi tongpaoApi;


    public TongpaoApi tongpaoApi(){
        if(tongpaoApi==null){
            tongpaoApi=getRetrofit(TongpaoApi.BASE_URL).create(TongpaoApi.class);
        }
        return tongpaoApi;
    }


    public Retrofit getRetrofit(String city){
        Retrofit build = new Retrofit.Builder()
                .baseUrl(city)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClienk())
                .build();
        return build;
    }

    public OkHttpClient getOkHttpClienk() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new interceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    static class interceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request build = request.newBuilder()
                    .addHeader("Authorization", "APPCODE 165b20d2a1a04e20a5e9e19f3b2200c2")
                    .build();

            return chain.proceed(build);
        }
    }
}
