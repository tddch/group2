package com.example.day1217tang.net;

import android.util.Log;


import com.example.day1217tang.api.ApiService;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpMannage {
    private static HttpMannage instance;
    private ApiService apiService;

    public static HttpMannage getInstance(){
        if(instance==null){
            synchronized (HttpMannage.class){
                if(instance==null){
                    instance = new HttpMannage();
                }
            }
        }
        return instance;
    }
    private Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeadInterceptor())
                .build();

        return  client;
    }

    private class HeadInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
//                    .addHeader("Authorization", "APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")

                    .build();
            HttpUrl url = request.url();
            Log.e("tag",url+"");
            return chain.proceed(request);
        }
    }
    public ApiService apiService(){
        if(apiService==null){
            apiService = getRetrofit(ApiService.BASE_URL)
                    .create(ApiService.class);
        }
        return apiService;
    }
}
