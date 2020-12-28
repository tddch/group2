package com.example.night1228;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}