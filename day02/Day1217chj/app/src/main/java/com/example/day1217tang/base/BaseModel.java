package com.example.day1217tang.base;





import com.example.day1217tang.interfaces.IBaseModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel implements IBaseModel {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);

    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }
}
