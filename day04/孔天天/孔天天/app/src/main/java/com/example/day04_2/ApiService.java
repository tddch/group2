package com.example.day04_2;

import com.example.day04_2.bean.ReWenBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    public static final String BEAN_URL="http://cdwan.cn:7000/tongpao/";
   /* http://cdwan.cn:7000/tongpao/discover/robe.json  袍子
    排行榜--等级榜 http://cdwan.cn:7000/tongpao/discover/rank_level.json
    排行榜--签到榜 http://cdwan.cn:7000/tongpao/discover/rank_sign.json
    排行榜--土豪榜 http://cdwan.cn:7000/tongpao/discover/rank_money.json
    热门活动：*/
   @GET("discover/hot_activity.json")
    Observable<ReWenBean> getReWenBean();
}
