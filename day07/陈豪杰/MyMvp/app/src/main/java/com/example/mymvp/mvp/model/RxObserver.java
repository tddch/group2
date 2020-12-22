package com.example.mymvp.mvp.model;

import android.util.Log;

import com.example.mymvp.R;
import com.example.mymvp.app.Mapp;
import com.example.mymvp.callback.RxCallBack;
import com.google.gson.JsonParseException;

import java.io.IOException;

import javax.net.ssl.SSLException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<T> implements Observer {
    private static final String TAG = "RxObserver";
    private RxCallBack<T> callBack;
    protected Disposable mDisposable;

    public RxObserver(RxCallBack<T> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(Object value) {
        if (callBack != null) {
            callBack.onSuccessData((T) value);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof IOException) {
            if (callBack != null)
                callBack.onErrorMsg(getString(R.string.connec_exception));
        } else if (e instanceof JsonParseException) {
            if (callBack != null)
                callBack.onErrorMsg(getString(R.string.jsonparse_exception));
        } else if (e instanceof SSLException) {
            if (callBack != null)
                callBack.onErrorMsg(getString(R.string.ssl_excetion));
        }
    }

    private String getString(int p) {
        return Mapp.context().getString(p);
    }

    @Override
    public void onComplete() {
        Log.e(TAG, Mapp.getStr(R.string.complete));
    }
}
