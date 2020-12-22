package com.example.shixun4.base.di.component;

import com.example.shixun4.base.mvp.mode1.OkManager;
import com.example.shixun4.base.mvp.mode1.RxOpretorlmpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = OkManager.class)
public interface OkComponent {
    void getSingleApiService(RxOpretorlmpl imp1);
}
