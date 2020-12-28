package com.example.liujian.inesenter;

public interface ICallBack<T> {
    void onSuccess(T t);
    void onFial(String error);
}
