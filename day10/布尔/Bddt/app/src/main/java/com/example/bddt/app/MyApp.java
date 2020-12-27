package com.example.bddt.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;


public class MyApp extends Application {

    private MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SDKInitializer.initialize(this);
    }
}
