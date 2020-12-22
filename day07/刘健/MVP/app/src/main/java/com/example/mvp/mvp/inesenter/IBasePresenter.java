package com.example.mvp.mvp.inesenter;

/**
 *
 */
public interface IBasePresenter<P> {
    void start();
    void start(P... p);
}
