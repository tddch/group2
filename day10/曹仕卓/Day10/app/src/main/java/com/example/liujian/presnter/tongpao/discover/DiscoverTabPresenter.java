package com.example.liujian.presnter.tongpao.discover;



import com.example.liujian.base.BasePresenter;
import com.example.liujian.bean.DiscoverTabRvBean;
import com.example.liujian.inesenter.ICallBack;
import com.example.liujian.inesenter.discover.IDiscoverTab;
import com.example.liujian.model.tontapo.discover.DiscoverTabModel;

/**
 *
 */
public class DiscoverTabPresenter extends BasePresenter<IDiscoverTab.View> implements IDiscoverTab.Presenter {
    IDiscoverTab.View view;
    IDiscoverTab.Model model;
    public DiscoverTabPresenter(IDiscoverTab.View view) {
        this.view = view;
        model=new DiscoverTabModel();
    }


    @Override
    public void getTabRv(int type) {
        this.model.loadTabRv(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getTabRvBean((DiscoverTabRvBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view!=null){
                    view.tips(error);
                }
            }
        },type);
    }
}
