package com.example.mymvp.mvp.model;

import com.example.mymvp.callback.RxCallBack;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

// TODO: 2020/12/22 用来封装各种操作符
public class RxOperator {
    public static Observable threadtransformer(Observable observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // TODO: 2020/12/22 封装了ConcatMap操作符 网络嵌套(注册完成之后自动登录)
    public <T> ObservableSource concatMap(Observable<T> registerObservable,
                                          Observable<T> loginObservable,
                                          RxCallBack<T> callBack) {
        return threadtransformer(registerObservable).doOnNext(object -> {
            callBack.onSuccessData((T) object);
        }).observeOn(Schedulers.io()).
                concatMap(object -> {
                    return loginObservable;
                });
    }
}
