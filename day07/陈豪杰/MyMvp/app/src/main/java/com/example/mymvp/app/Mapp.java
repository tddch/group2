package com.example.mymvp.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Mapp extends Application {

    private static Context mcontext;


    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;

    }

    public static Context context() {
        return mcontext;
    }
    public static String getStr(int p){
        return Mapp.context().getString(p);
    }

}
