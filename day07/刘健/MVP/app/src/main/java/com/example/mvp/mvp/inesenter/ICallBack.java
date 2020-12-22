package com.example.mvp.mvp.inesenter;

public interface ICallBack<T> {
    void onSuccess(T t);
    void onError(String msg);
}
