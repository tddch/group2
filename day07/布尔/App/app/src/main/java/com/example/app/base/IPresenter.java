package com.example.app.base;

public interface IPresenter<T> {
    void start();
    void start(T... t);
}