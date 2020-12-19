package com.example.day1217tang.base;





import com.example.day1217tang.interfaces.IBasePresenter;
import com.example.day1217tang.interfaces.IBaseView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter <V extends IBaseView> implements IBasePresenter<V> {
    protected V mView;
    private WeakReference<V> weakReference;

    @Override
    public void onAttachView(V view) {
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }

    @Override
    public void onDetachView() {
        mView =null;
    }



}
