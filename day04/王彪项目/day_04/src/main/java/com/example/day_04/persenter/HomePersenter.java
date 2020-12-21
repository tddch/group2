package com.example.day_04.persenter;

import com.example.day_04.back.HomeBack;
import com.example.day_04.modle.HomeGson;
import com.example.day_04.view.IView;

public class HomePersenter {
    private IView view;
    private final HomeGson homeGson;


    public HomePersenter(IView view) {
        this.view = view;
        homeGson = new HomeGson();
    }

    public void start() {
        homeGson.start(new HomeBack() {
            @Override
            public void showdui(Object object) {
                view.showdui(object);
            }

            @Override
            public void showcuo(String msg) {
                view.showcuo(msg);
            }
        });
    }
}
