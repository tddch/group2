package com.example.liujian.presnter.tongpao.discover;


import com.example.liujian.base.BasePresenter;
import com.example.liujian.bean.DiscoverTabBean;
import com.example.liujian.bean.DiscoverTopicBean;
import com.example.liujian.inesenter.ICallBack;
import com.example.liujian.inesenter.discover.IDiscover;
import com.example.liujian.model.tontapo.discover.DiscoverModel;

/**
 *
 */
public class DiscoverPresenter extends BasePresenter<IDiscover.View> implements IDiscover.Presenter {
    IDiscover.View view;
    IDiscover.Model model;
    public DiscoverPresenter(IDiscover.View view) {
        this.view = view;
        model=new DiscoverModel();
    }

    @Override
    public void getTopic() {
        this.model.loadTopic(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getTopicBean((DiscoverTopicBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view!=null){
                    view.tips(error);
                }
            }
        });
    }

    @Override
    public void getTab() {
        this.model.loadTab(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getTabBean((DiscoverTabBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view!=null){
                    view.tips(error);
                }
            }
        });
    }


}
