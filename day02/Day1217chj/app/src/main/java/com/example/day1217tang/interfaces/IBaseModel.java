package com.example.day1217tang.interfaces;

import io.reactivex.disposables.Disposable;

public interface IBaseModel {
    void onDisposable(Disposable disposable);

    void clear();
}
