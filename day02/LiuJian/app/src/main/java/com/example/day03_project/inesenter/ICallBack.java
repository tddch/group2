package com.example.day03_project.inesenter;

public interface ICallBack<T> {
    void onSuccess(T t);
    void onFial(String error);
}
