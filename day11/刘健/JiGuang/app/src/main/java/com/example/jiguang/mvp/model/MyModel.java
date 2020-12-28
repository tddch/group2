package com.example.jiguang.mvp.model;

import com.example.jiguang.R;
import com.example.jiguang.mvp.model.bean.BannerBean;

/**
 *
 */
public class MyModel {
    public void getBanner(MyPresenter<BannerBean> fuLiBeanMyPresenter){
        BannerBean bannerBean  = new BannerBean();
        for (int i = 0; i < 3; i++) {
           bannerBean.setImage(R.mipmap.ic_launcher);
           bannerBean.setTitle("啊巴啊巴" + i);

        }
        fuLiBeanMyPresenter.getData(bannerBean);
    }
}
