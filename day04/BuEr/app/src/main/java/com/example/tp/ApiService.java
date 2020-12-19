package com.example.tp;

import com.example.tp.bean.AsscoBean;
import com.example.tp.bean.DengBean;
import com.example.tp.bean.DiscoverBean;
import com.example.tp.bean.HaoBean;
import com.example.tp.bean.HotspotBean;
import com.example.tp.bean.QianBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    String dUrl = "http://cdwan.cn:7000/tongpao/";
    @GET("discover/hot_activity.json")
    Observable<DiscoverBean> reData();

    String hUrl = "http://cdwan.cn:7000/tongpao/";
    @GET("discover/news_1.json")
    Observable<HotspotBean> getHotspot();

    String sUrl = "http://cdwan.cn:7000/tongpao/";
    @GET("discover/association.json")
    Observable<AsscoBean> getAssoc();

    String oUrl = "http://cdwan.cn:7000/tongpao/";
    @GET("discover/rank_level.json")
    Observable<HaoBean> getHao();

    String jUrl = "http://cdwan.cn:7000/tongpao/";
    @GET("discover/rank_sign.json")
    Observable<DengBean> getDeng();

    String qUrl = "http://cdwan.cn:7000/tongpao/";
    @GET("discover/rank_money.json")
    Observable<QianBean> getQian();
}
