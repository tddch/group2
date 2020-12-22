package com.example.mvp.mvp.inesenter;

/**
 *
 */
public interface IBaseView<T> {
    void onSuccess(T t);
    void onError(String msg);
}
