package com.example.liujian.inesenter;

public interface IBasePresenter <V extends IBaseView>  {
    void attachView(V v);
    void unAttachView();
}
