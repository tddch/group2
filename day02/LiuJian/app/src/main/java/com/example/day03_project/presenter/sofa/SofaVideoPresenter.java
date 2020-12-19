package com.example.day03_project.presenter.sofa;

import android.util.Log;

import com.example.day03_project.base.BasePresenter;
import com.example.day03_project.inesenter.ICallBack;
import com.example.day03_project.inesenter.sofa.ISodaVideo;
import com.example.day03_project.inesenter.sofa.ISofa;
import com.example.day03_project.model.sofa.SofaImgModel;
import com.example.day03_project.model.sofa.SofaVideoModel;
import com.example.day03_project.model.sofa.data.SofaImgBean;
import com.example.day03_project.model.sofa.data.SofaVideoBean;

/**
 *
 */
public class SofaVideoPresenter extends BasePresenter<ISodaVideo.View> implements ISodaVideo.Presenter {
    ISodaVideo.View view;
    ISodaVideo.Model model;
    public SofaVideoPresenter(ISodaVideo.View view) {
        this.view = view;
        model = new SofaVideoModel();
    }




    @Override
    public void getVideo() {
        model.loadVideo(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){

                    view.getVideo((SofaVideoBean) o);
                }

            }

            @Override
            public void onFial(String error) {
                if(view!=null){
                    Log.e("TAG", "onFial: "+error );
                }
            }
        });
    }
}
