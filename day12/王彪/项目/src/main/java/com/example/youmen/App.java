package com.example.youmen;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
         initUm();
    }

    private void initUm() {
        UMConfigure.init(this, "5a12384aa40fa3551f0001d1"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        // 微信设置
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setWXFileProvider("com.example.youmen.fileprovider");
        // QQ设置
        PlatformConfig.setQQZone("101830139", "5d63ae8858f1caab67715ccd6c18d7a5");
        PlatformConfig.setQQFileProvider("com.example.youmen.fileprovider");
        // 企业微信设置
        PlatformConfig.setWXWork("wwac6ffb259ff6f66a", "EU1LRsWC5uWn6KUuYOiWUpkoH45eOA0yH-ngL8579zs", "1000002", "wwauthac6ffb259ff6f66a000002");
        PlatformConfig.setWXWorkFileProvider("com.example.youmen.fileprovider");
        // 其他平台设置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");

    }
}