package com.example.liujian.inesenter.discover;


import com.example.liujian.bean.DiscoverTabRvBean;
import com.example.liujian.inesenter.IBasePresenter;
import com.example.liujian.inesenter.IBaseView;
import com.example.liujian.inesenter.ICallBack;
import com.example.liujian.inesenter.IModel;

public interface IDiscoverTab {
    interface View extends IBaseView {
        void getTabRvBean(DiscoverTabRvBean tabRvBean);
    }

    interface Presenter extends IBasePresenter<View> {

        void getTabRv(int type);
    }

    interface Model extends IModel {
        void loadTabRv(ICallBack callBack, int type);
    }
}
