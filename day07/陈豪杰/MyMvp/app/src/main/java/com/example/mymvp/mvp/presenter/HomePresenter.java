package com.example.mymvp.mvp.presenter;

import com.example.mymvp.base.BasePresenter;
import com.example.mymvp.callback.RxCallBack;
import com.example.mymvp.mvp.model.HomeModel;
import com.example.mymvp.mvp.model.RxOpretorlmpl;
import com.example.mymvp.mvp.model.constants.ApiConstans;
import com.example.mymvp.mvp.ui.fragments.HomeFragment;

import java.io.IOException;

import okhttp3.ResponseBody;

public class HomePresenter extends BasePresenter {
    private RxOpretorlmpl model;
    private HomeFragment homeFragment;

    public HomePresenter(HomeFragment homeFragment) {
       model=new RxOpretorlmpl();
        this.homeFragment = homeFragment;
    }

    @Override
    public void start() {
        super.start();
        // TODO: 2020/12/22 请求数据
        model.rxGetRequest(ApiConstans.Hot_Activity, new RxCallBack<Object>() {
            @Override
            public void onSuccessData(Object o) {
                if(homeFragment!=null){
                    ResponseBody responseBody = (ResponseBody) o;
                    try {
                        String string = responseBody.string();
                        homeFragment.onSuccess(string);
                    } catch (IOException e) {
                        e.printStackTrace();
                        homeFragment
                                .onError(e.getMessage());
                    }
                }
            }

            @Override
            public void onErrorMsg(String msg) {
                if (homeFragment != null) {
                    homeFragment.onError(msg);
                }
            }
        });

    }
}
