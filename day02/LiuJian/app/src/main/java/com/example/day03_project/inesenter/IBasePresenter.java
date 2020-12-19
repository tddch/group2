package com.example.day03_project.inesenter;

public interface IBasePresenter <V extends IBaseView>  {
    void attachView(V v);
    void unAttachView();
}
