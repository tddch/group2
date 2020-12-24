package com.example.liujian.inesenter.discover;


import com.example.liujian.bean.DiscoverTabBean;
import com.example.liujian.bean.DiscoverTopicBean;
import com.example.liujian.inesenter.IBasePresenter;
import com.example.liujian.inesenter.IBaseView;
import com.example.liujian.inesenter.ICallBack;
import com.example.liujian.inesenter.IModel;

public interface IDiscover {
    interface View extends IBaseView {
        void getTopicBean(DiscoverTopicBean topicBean);

        void getTabBean(DiscoverTabBean tabBean);

    }

    interface Presenter extends IBasePresenter<View> {
        void getTopic();

        void getTab();

    }

    interface Model extends IModel {
        void loadTopic(ICallBack callBack);

        void loadTab(ICallBack callBack);

    }
}
