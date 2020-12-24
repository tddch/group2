package com.example.liujian.net;

import android.text.TextUtils;


import com.example.liujian.inesenter.ICallBack;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 *
 */
public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private ICallBack callback;
    private String errorMsg;
    private boolean isShowErrorState = false;

    protected CommonSubscriber(ICallBack callback){
        this.callback = callback;
    }

    protected CommonSubscriber(ICallBack callback, String emsg){
        this.callback = callback;
        errorMsg = emsg;
    }

    @Override
    public void onError(Throwable t) {
        //LoadingUtil.getInstance().hideLoading();
        if(callback == null) return;
        if(errorMsg != null && TextUtils.isEmpty(errorMsg)){
            callback.onFial("错误"+errorMsg);
        }
    }

    @Override
    public void onComplete() {
        //LoadingUtil.getInstance().hideLoading();
        //mView.showLoading(View.GONE);
    }
}
