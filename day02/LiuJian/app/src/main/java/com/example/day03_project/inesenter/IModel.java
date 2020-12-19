package com.example.day03_project.inesenter;

import io.reactivex.disposables.Disposable;

/**
 *
 */
public interface IModel {
    void addDisposable(Disposable disposable);
    void clear();
}
