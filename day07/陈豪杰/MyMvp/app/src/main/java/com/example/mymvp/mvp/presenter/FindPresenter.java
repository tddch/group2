package com.example.mymvp.mvp.presenter;

import com.example.mymvp.base.BasePresenter;
import com.example.mymvp.callback.RxCallBack;
import com.example.mymvp.mvp.model.RxOpretorlmpl;
import com.example.mymvp.mvp.model.constants.ApiConstans;
import com.example.mymvp.mvp.ui.fragments.FindFragment;
import com.example.mymvp.mvp.ui.fragments.HomeFragment;

import java.io.IOException;

import okhttp3.ResponseBody;

public class FindPresenter extends BasePresenter {
    private RxOpretorlmpl model;
    private FindFragment findFragment;

    public FindPresenter(FindFragment findFragment) {
        model=new RxOpretorlmpl();
        this.findFragment = findFragment;
    }

    @Override
    public void start() {
        super.start();
        // TODO: 2020/12/22 请求数据
        model.rxGetRequest(ApiConstans.Hot_Activity, new RxCallBack<Object>() {
            @Override
            public void onSuccessData(Object o) {
                if(findFragment!=null){
                    ResponseBody responseBody = (ResponseBody) o;
                    try {
                        String string = responseBody.string();
                        findFragment.onSuccess(string);
                    } catch (IOException e) {
                        e.printStackTrace();
                        findFragment
                                .onError(e.getMessage());
                    }
                }
            }

            @Override
            public void onErrorMsg(String msg) {
                if (findFragment != null) {
                    findFragment.onError(msg);
                }
            }
        });

    }
}
