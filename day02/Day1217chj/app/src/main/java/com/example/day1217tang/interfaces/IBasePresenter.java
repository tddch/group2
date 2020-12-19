package com.example.day1217tang.interfaces;

public interface IBasePresenter<V extends IBaseView>{
    void onAttachView(V view);
    void onDetachView();

}
