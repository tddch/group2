package com.example.mymvp.base.interfaces;

public interface IBaseView<T> {
    void onSuccess(T t);
    void onError(String msg);
}
