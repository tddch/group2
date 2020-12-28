package com.example.jiguang.base;

public interface IBasePresenter<T> {

    void start();
    void start(T... t);

}
