package com.example.mymvp.mvp.model;

import com.example.mymvp.callback.RxCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel {

    public void requestData(RxCallBack rxCallBack) {
        Request request = new Request.Builder().
                url("https://fanyi.baidu.com/?aldtype=85#en/zh/detach").build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                rxCallBack.onErrorMsg(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                rxCallBack.onSuccessData(string);
            }
        });
    }
}
