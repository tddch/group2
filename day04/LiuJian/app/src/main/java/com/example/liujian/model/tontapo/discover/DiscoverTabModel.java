package com.example.liujian.model.tontapo.discover;

import com.example.liujian.base.BaseModel;
import com.example.liujian.bean.DiscoverTabRvBean;
import com.example.liujian.inesenter.ICallBack;
import com.example.liujian.inesenter.discover.IDiscoverTab;
import com.example.liujian.net.CommonSubscriber;
import com.example.liujian.net.HttpManager;
import com.example.liujian.utils.RxUtils;

/**
 *
 */
public class DiscoverTabModel extends BaseModel implements IDiscoverTab.Model {


    @Override
    public void loadTabRv(ICallBack callBack, int type) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getDiscoverTabRv(type)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTabRvBean>(callBack) {
                            @Override
                            public void onNext(DiscoverTabRvBean discoverTabRvBean) {
                                callBack.onSuccess(discoverTabRvBean);

                            }
                        })
        );
    }
}
