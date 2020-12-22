package com.example.mymvp.di.component;


import com.example.mymvp.mvp.model.OkManager;
import com.example.mymvp.mvp.model.RxOpretorlmpl;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = OkManager.class)
public interface OkComponent {
    // TODO: 2020/12/22 自定义的方法
    void getSingleApiService(RxOpretorlmpl impl);
}
