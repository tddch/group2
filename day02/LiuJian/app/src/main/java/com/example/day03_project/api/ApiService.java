package com.example.day03_project.api;

import com.example.day03_project.model.discover.DiscoverRecommendBean;
import com.example.day03_project.model.sofa.data.SofaImgBean;
import com.example.day03_project.model.sofa.data.SofaTextBean;
import com.example.day03_project.model.sofa.data.SofaVideoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL="http://123.56.232.18:8080/serverdemo/";

    @GET("feeds/queryHotFeedsList?pageCount=12&feedType=pics")
    Observable<SofaImgBean> getImgData();

    @GET("feeds/queryHotFeedsList?pageCount=12&feedType=video")
    Observable<SofaVideoBean> getVideoData();

    @GET("feeds/queryHotFeedsList?pageCount=10&feedType=text&feedId=1578920275")
    Observable<SofaTextBean> getTextData();

    @GET("tag/queryTagList")
    Observable<DiscoverRecommendBean> getDiscoverRecommendData();
}
