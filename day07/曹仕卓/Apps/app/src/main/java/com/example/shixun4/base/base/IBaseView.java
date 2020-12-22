package com.example.shixun4.base.base;

public interface IBaseView<T> {
    void onScuccess(T t);
    void onError(String msg);
}
