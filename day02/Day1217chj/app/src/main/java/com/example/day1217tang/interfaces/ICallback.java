package com.example.day1217tang.interfaces;

public interface ICallback <T>{
    void onSuccess(T t);
    void onError(String msg);
}
