package com.example.day1217tang.api;



//http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=12&feedType=pics  沙发—图片
//        http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=12&feedType=video 沙发—视频
//        http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=10&feedType=text&feedId=1578920275沙发— 文本
//        http://123.56.232.18:8080/serverdemo//tag/queryTagList          发现—推荐


import com.example.day1217tang.model.bean.FindMain;
import com.example.day1217tang.model.bean.SofoMp4;
import com.example.day1217tang.model.bean.SofoPicture;
import com.example.day1217tang.model.bean.SofoText;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {
 String BASE_URL = "http://123.56.232.18:8080/serverdemo/";
 @GET("feeds/queryHotFeedsList?pageCount=10&feedType=text&feedId=1578920275")
 Flowable<SofoText> getText();
 @GET("feeds/queryHotFeedsList?pageCount=12&feedType=video")
 Flowable<SofoMp4> getMp4();
 @GET("feeds/queryHotFeedsList?pageCount=12&feedType=pics")
 Flowable<SofoPicture> getPicture();
 @GET("tag/queryTagList")
 Flowable<FindMain> getFindMain();
}
