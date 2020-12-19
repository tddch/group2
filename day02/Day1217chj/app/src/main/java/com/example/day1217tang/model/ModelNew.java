package com.example.day1217tang.model;


import com.example.day1217tang.base.BaseModel;
import com.example.day1217tang.interfaces.ICallback;
import com.example.day1217tang.interfaces.INew;
import com.example.day1217tang.model.bean.FindMain;
import com.example.day1217tang.model.bean.SofoMp4;
import com.example.day1217tang.model.bean.SofoPicture;
import com.example.day1217tang.model.bean.SofoText;
import com.example.day1217tang.net.CommonSubscriber;
import com.example.day1217tang.net.HttpMannage;
import com.example.day1217tang.utils.RxUtils;

public class ModelNew extends BaseModel implements INew.Model {


    @Override
    public void getSofoPicture(ICallback callback) {
        onDisposable( HttpMannage.getInstance().apiService().getPicture()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SofoPicture>(callback) {
                    @Override
                    public void onNext(SofoPicture sofoPicture) {
                        if(callback!=null){
                            callback.onSuccess(sofoPicture);
                        }
                    }
                }));
    }

    @Override
    public void getSofoMp4(ICallback callback) {
        onDisposable( HttpMannage.getInstance().apiService().getMp4()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SofoMp4>(callback) {
                    @Override
                    public void onNext(SofoMp4 sofoMp4) {
                        if(callback!=null){
                            callback.onSuccess(sofoMp4);
                        }
                    }
                }));
    }

    @Override
    public void getSofoText(ICallback callback) {
        onDisposable( HttpMannage.getInstance().apiService().getText()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SofoText>(callback) {
                    @Override
                    public void onNext(SofoText sofoText) {
                        if(callback!=null){
                            callback.onSuccess(sofoText);
                        }
                    }
                }));
    }

    @Override
    public void getFindMain(ICallback callback) {
        onDisposable( HttpMannage.getInstance().apiService().getFindMain()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<FindMain>(callback) {
                    @Override
                    public void onNext(FindMain findMain) {
                        if(callback!=null){
                            callback.onSuccess(findMain);
                        }
                    }
                }));
    }
}
