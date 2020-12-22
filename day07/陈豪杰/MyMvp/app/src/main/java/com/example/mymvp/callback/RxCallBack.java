package com.example.mymvp.callback;

public interface RxCallBack<T> {
    void onSuccessData(T t);
    void onErrorMsg(String msg);
}
