package com.example.mymvp.base;

import com.example.mymvp.base.interfaces.IBasePresenter;
import com.example.mymvp.base.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IBaseView<T>,T> implements IBasePresenter<T> {
private WeakReference<V> mView;
private CompositeDisposable mComposite = new CompositeDisposable();

    // TODO: 2020/12/21 添加网络开关
    protected  void addDisposable(Disposable disposable){
        if(mComposite!=null){
            mComposite.add(disposable);
        }

    }
    //弱引用修饰V层 方便GC回收
    protected void attachView(V view){
        mView = new WeakReference<V>(view);
    }

    // TODO: 2020/12/21 释放V层资源的同事断开网络开关
    protected void detachView(){
        if(mView!=null){
            mView.clear();
            mView = null;
        }

    }

    // TODO: 2020/12/21 断开网络开关
    private void detachDisposable(){
        if(mComposite!=null&&mComposite.isDisposed()){
            mComposite.dispose();
            mComposite.clear();
            mComposite = null;
        }
    }
    @Override
    public void start() {

    }

    @Override
    public void start(T... t) {

    }


}
