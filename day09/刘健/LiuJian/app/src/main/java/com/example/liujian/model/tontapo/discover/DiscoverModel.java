package com.example.liujian.model.tontapo.discover;


import com.example.liujian.base.BaseModel;
import com.example.liujian.bean.DiscoverTabBean;
import com.example.liujian.bean.DiscoverTopicBean;
import com.example.liujian.inesenter.ICallBack;
import com.example.liujian.inesenter.discover.IDiscover;
import com.example.liujian.net.CommonSubscriber;
import com.example.liujian.net.HttpManager;
import com.example.liujian.utils.RxUtils;

/**
 *
 */
public class DiscoverModel extends BaseModel implements IDiscover.Model {
    @Override
    public void loadTopic(ICallBack callBack) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getDiscoverTopic()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTopicBean>(callBack) {
                            @Override
                            public void onNext(DiscoverTopicBean topicBean) {
                                callBack.onSuccess(topicBean);
                            }
                        })
        );
    }

    @Override
    public void loadTab(ICallBack callBack) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getDiscoverTab()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTabBean>(callBack) {
                            @Override
                            public void onNext(DiscoverTabBean tabBean) {
                                callBack.onSuccess(tabBean);
                            }
                        })
        );
    }


}
