package com.example.day_04.modle;

import com.example.day_04.modle.bean.JingYanBean;
import com.example.day_04.modle.bean.QianDaoBean;
import com.example.day_04.modle.bean.RecyBean;
import com.example.day_04.modle.bean.TuHaoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    String BASE_URL = "http://cdwan.cn:7000/";

    //http://cdwan.cn:7000/tongpao/discover/hot_activity.json

    @GET("tongpao/discover/hot_activity.json")
    Observable<RecyBean> getData();

    //经验
    //http://cdwan.cn:7000/tongpao/discover/rank_level.json
    @GET("tongpao/discover/rank_level.json")
    Observable<JingYanBean> getJing();

    //签到
    //http://cdwan.cn:7000/tongpao/discover/rank_sign.json
    @GET("tongpao/discover/rank_sign.json")
    Observable<QianDaoBean> getQian();


    //土豪
    @GET("tongpao/discover/rank_money.json")
    Observable<TuHaoBean> getTu();
}
