package com.example.liujian.base;


import com.example.liujian.inesenter.IModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *
 */
public abstract class BaseModel implements IModel {
    CompositeDisposable disposableSet=new CompositeDisposable();

    @Override
    public void addDisposable(Disposable disposable) {
        disposableSet.add(disposable);
    }

    @Override
    public void clear() {
        disposableSet.clear();
    }

    //    @Override
//    public void addDisposable(Disposable disposable) {
//        disposableSet(disposable);
//    }
//
//
//
//    @Override
//    public void clear() {
//        disposableSet.clear();
//    }
}
