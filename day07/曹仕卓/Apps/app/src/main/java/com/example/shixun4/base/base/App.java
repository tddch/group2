package com.example.shixun4.base.base;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mContext = this;
    }
    public static Context context(){
        return mContext;
    }
    public static String getStr(int p){
        return App.mContext.getString(p);
    }
}
