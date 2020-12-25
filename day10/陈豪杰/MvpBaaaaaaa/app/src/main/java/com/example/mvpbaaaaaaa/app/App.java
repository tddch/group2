package com.example.mvpbaaaaaaa.app;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

public class App extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
      initMap();

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
    private void initMap() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
    public static Context context() {
        return mContext;
    }

    public static String getStr(int p) {
        return App.context().getString(p);
    }
}
