package com.example.xmu;

import com.example.xmu.bean.ShiBean;
import com.example.xmu.bean.TuBean;
import com.example.xmu.bean.TuiBean;
import com.example.xmu.bean.WenBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String tUrl = "http://123.56.232.18:8080/serverdemo/";
    @GET("feeds/queryHotFeedsList?pageCount=12&feedType=pics")
    Observable<TuBean> TuData();

    String wUrl = "http://123.56.232.18:8080/serverdemo/";
    @GET("feeds/queryHotFeedsList?pageCount=10&feedType=text&feedId=1578920275")
    Observable<WenBean> WenData();

    String sUrl = "http://123.56.232.18:8080/serverdemo/";
    @GET("feeds/queryHotFeedsList?pageCount=12&feedType=video")
    Observable<ShiBean> ShiData();

    String jUrl = "http://123.56.232.18:8080/serverdemo/";
    @GET("/tag/queryTagList")
    Observable<TuiBean> JianData();

}
