package com.example.mymvp.mvp.model;


import com.example.mymvp.callback.RxCallBack;
import com.example.mymvp.di.component.DaggerOkComponent;
import com.example.mymvp.mvp.model.api.ApiService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class RxOpretorlmpl {
    @Inject
    ApiService mApiservice;

    public RxOpretorlmpl() {
        DaggerOkComponent.create()
                .getSingleApiService(this);
    }

    // TODO: 2020/12/22 封装一个get请求的方法
    public <T> void rxGetRequest(String url, RxCallBack<T> callBack) {
       RxOperator.threadtransformer(mApiservice.requestGetData(url))
                .subscribe(new RxObserver(callBack));
    }


    //TODO 有参数没有请求头的get请求
    public <T> void rxGetRequest(String url, Map<String, T> params,
                                 RxCallBack<T> callBack) {
        if (params != null && params.size() > 0) {
            RxOperator.threadtransformer(mApiservice.requestGetData(url, params))
                    .subscribe(new RxObserver(callBack));
        } else {
            rxGetRequest(url, callBack);
        }
    }

    // TODO: 2020/12/22 有请求头但是没有参数的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers
            , RxCallBack<T> callBack) {
        if (headers != null && headers.size() > 0) {
            RxOperator.threadtransformer(mApiservice.requestGetData(url, headers))
                    .subscribe(new RxObserver(callBack));

        } else {
            rxGetRequest(url, callBack);
        }
    }

    // TODO: 2020/12/22 有参数有请求头
//    public <T> void rxGetRequest(String url,){
//
//    }
}
