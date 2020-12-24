package com.example.day_07.model;

import com.example.day_07.model.bean.RecyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {


    String BASE_URL = "http://cdwan.cn:7000/";

    //http://cdwan.cn:7000/tongpao/discover/hot_activity.json

    @GET("tongpao/discover/hot_activity.json")
    Observable<RecyBean> getData();

}
