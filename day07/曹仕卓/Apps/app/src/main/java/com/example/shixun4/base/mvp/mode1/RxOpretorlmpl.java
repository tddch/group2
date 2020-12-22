package com.example.shixun4.base.mvp.mode1;


import com.example.shixun4.base.mvp.mode1.api.ApiService;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;

public class RxOpretorlmpl {
    @Inject
    ApiService mApiService;

    public RxOpretorlmpl() {

    }



 /*   //没有参数也没有请求头的get请求
    public <T> void rxGetRequest(String url, RxObserverCallBack<T> callBack) {
        RxOperator.threadtransformer(mApiService.requestGetData(url)).
                subscribe(new RxObserver(callBack));
    }



    //有参数没有请求头的get请求
    public <T> void rxGetRequest(String url, Map<String, T> params,
                                 RxObserverCallBack<T> callBack) {
        if (params != null && params.size() > 0) { //有参数的请求
            RxOperator.threadtransformer(mApiService.requestGetData(url, params)).
                    subscribe(new RxObserver(callBack));
        } else {  //没有参数的请求
            rxGetRequest(url, callBack);
        }
    }


    //有请求头但是没有参数的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers,
                                 RxObserverCallBack<T> callBack) {
        if (headers != null && headers.size() > 0) {
            RxOperator.threadtransformer(mApiService.requestGetData(url, headers)).
                    subscribe(new RxObserver(callBack));
        } else {
            rxGetRequest(url, callBack);
        }
    }





    //没有参数也没有请求头的post请求
    public <T> void rxPostRequest(String url, RxObserverCallBack<T> callBack) {
        RxOperator.threadtransformer(mApiService.requestPostData(url)).
                subscribe(new RxObserver(callBack));
    }*/
}
