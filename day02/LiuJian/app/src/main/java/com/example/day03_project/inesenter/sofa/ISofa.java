package com.example.day03_project.inesenter.sofa;

import com.example.day03_project.inesenter.IBasePresenter;
import com.example.day03_project.inesenter.IBaseView;
import com.example.day03_project.inesenter.ICallBack;
import com.example.day03_project.inesenter.IModel;
import com.example.day03_project.model.sofa.data.SofaImgBean;

/**
 *
 */
public interface ISofa {
    interface View extends IBaseView{
        void getImg(SofaImgBean imgBean);
    }

    interface Model extends IModel{
        void loadImg(ICallBack callBack);
    }

    interface Presenter extends IBasePresenter<View>{
        void getImg();
    }
}
