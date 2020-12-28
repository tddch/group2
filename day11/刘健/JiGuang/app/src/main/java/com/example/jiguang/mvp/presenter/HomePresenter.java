package com.example.jiguang.mvp.presenter;

import com.example.jiguang.base.BasePresenter;
import com.example.jiguang.mvp.model.MyModel;
import com.example.jiguang.mvp.model.MyPresenter;
import com.example.jiguang.mvp.model.bean.BannerBean;
import com.example.jiguang.mvp.ui.fragment.HomeFragment;

/**
 *
 */
public class HomePresenter extends BasePresenter {
    private HomeFragment homeFragment;
    private MyModel model;
    public HomePresenter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
        model=new MyModel();
    }

    @Override
    public void start() {
        model.getBanner(new MyPresenter<BannerBean>() {
            @Override
            public void getData(BannerBean bannerBean) {
                homeFragment.getData(bannerBean);
            }
        });
    }

    @Override
    public void start(Object[] t) {

    }
}
