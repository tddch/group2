package com.example.day1217tang.presenter;


import com.example.day1217tang.base.BasePresenter;

import com.example.day1217tang.model.ModelNew;
import com.example.day1217tang.interfaces.ICallback;
import com.example.day1217tang.interfaces.INew;
import com.example.day1217tang.model.bean.FindMain;
import com.example.day1217tang.model.bean.SofoMp4;
import com.example.day1217tang.model.bean.SofoPicture;
import com.example.day1217tang.model.bean.SofoText;

public class PresenterNew extends BasePresenter<INew.View> implements INew.Presenter {
    INew.View view;
    INew.Model model;

    public PresenterNew(INew.View view) {
        this.view = view;
        model = new ModelNew();
    }


    @Override
    public void getSofoPicture() {
        model.getSofoPicture(new ICallback() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getSofoPicture((SofoPicture) o);
                }

            }

            @Override
            public void onError(String msg) {
                view.onTips(msg);
            }
        });
    }

    @Override
    public void getSofoMp4() {
        model.getSofoMp4(new ICallback() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getSofoMp4((SofoMp4) o);
                }
            }

            @Override
            public void onError(String msg) {
                view.onTips(msg);
            }
        });
    }

    @Override
    public void getSofoText() {
        model.getSofoText(new ICallback() {
            @Override
            public void onSuccess(Object o) {
                view.getSofoText((SofoText) o);
            }

            @Override
            public void onError(String msg) {
                view.onTips(msg);
            }
        });
    }

    @Override
    public void getFindMain() {
        model.getFindMain(new ICallback() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getFindMain((FindMain) o);
                }
            }

            @Override
            public void onError(String msg) {
                view.onTips(msg);
            }
        });
    }
}
