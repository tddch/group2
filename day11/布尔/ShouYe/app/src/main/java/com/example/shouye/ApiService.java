package com.example.shouye;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String Url = "https://gank.io/";
    @GET("api/data/%E7%A6%8F%E5%88%A9/30/3")
    Observable<Bean> GirlData();
}
