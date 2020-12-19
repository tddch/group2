package com.example.day03_project.model.sofa;

import com.example.day03_project.base.BaseModel;
import com.example.day03_project.inesenter.ICallBack;
import com.example.day03_project.inesenter.sofa.ISodaVideo;
import com.example.day03_project.inesenter.sofa.ISofa;
import com.example.day03_project.model.sofa.data.SofaImgBean;
import com.example.day03_project.model.sofa.data.SofaVideoBean;
import com.example.day03_project.net.HttpManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class SofaVideoModel extends BaseModel implements ISodaVideo.Model {

    @Override
    public void loadVideo(ICallBack callBack) {
        HttpManager.getInstance().apiService().getVideoData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SofaVideoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SofaVideoBean sofaImgBean) {
                        callBack.onSuccess(sofaImgBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFial(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
