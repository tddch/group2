package com.example.mvp.mvp.mvp.presenter;

import com.example.mvp.mvp.base.BasePresenter;
import com.example.mvp.mvp.callback.RxObserverCallBack;
import com.example.mvp.mvp.mvp.model.RxOpretorImpl;
import com.example.mvp.mvp.mvp.model.bean.DiscoverTopicBean;
import com.example.mvp.mvp.mvp.model.constants.ApiConstants;
import com.example.mvp.mvp.mvp.ui.fragment.discover.DiscoverFragment;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 *
 */
public class DiscoverPresenter extends BasePresenter {

    private final RxOpretorImpl mImpl;
    private  DiscoverFragment discoverFragment;

    public DiscoverPresenter(DiscoverFragment fragment) {
        mImpl = new RxOpretorImpl();
        this.discoverFragment=fragment;
    }

    @Override
    public void start() {
        mImpl.rxGetRequest(ApiConstants.DEBUG_BASE_URL, new RxObserverCallBack<Object>() {
            @Override
            public void onSuccessData(Object obj) {
                if(discoverFragment!=null){
                    ResponseBody responseBody = (ResponseBody) obj;

                    try {
                        String s = responseBody.string();
                        discoverFragment.onSuccess(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                        discoverFragment.onError(e.getMessage());
                    }
                }
            }

            @Override
            public void onErrorMsg(String msg) {

            }
        });
    }
}
