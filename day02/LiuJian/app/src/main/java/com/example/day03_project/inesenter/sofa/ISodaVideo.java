package com.example.day03_project.inesenter.sofa;

import com.example.day03_project.inesenter.IBasePresenter;
import com.example.day03_project.inesenter.IBaseView;
import com.example.day03_project.inesenter.ICallBack;
import com.example.day03_project.inesenter.IModel;
import com.example.day03_project.model.sofa.data.SofaVideoBean;

public interface ISodaVideo  {
    interface View extends IBaseView{
        void getVideo(SofaVideoBean sofaVideoBean);
    }
    interface Model extends IModel{
        void loadVideo(ICallBack callBack);
    }

    interface Presenter extends IBasePresenter<View>{
        void getVideo();
    }
}
