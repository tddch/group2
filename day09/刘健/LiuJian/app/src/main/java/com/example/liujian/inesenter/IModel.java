package com.example.liujian.inesenter;

import io.reactivex.disposables.Disposable;

/**
 *
 */
public interface IModel {
    void addDisposable(Disposable disposable);
    void clear();
}
