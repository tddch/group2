package com.example.day1217tang.interfaces;


import com.example.day1217tang.model.bean.FindMain;
import com.example.day1217tang.model.bean.SofoMp4;
import com.example.day1217tang.model.bean.SofoPicture;
import com.example.day1217tang.model.bean.SofoText;

public interface INew {
    interface View extends IBaseView{
        void getSofoPicture(SofoPicture sofoPicture);
        void getSofoMp4(SofoMp4 sofoMp4);
        void getSofoText(SofoText sofoText);
        void getFindMain(FindMain findMain);

    }
        interface Presenter extends IBasePresenter<View> {
        void getSofoPicture();
        void getSofoMp4();
        void getSofoText();
        void getFindMain();

    }
    interface Model extends IBaseModel{

        void getSofoPicture(ICallback callback);
        void getSofoMp4(ICallback callback);
        void getSofoText(ICallback callback);
        void getFindMain(ICallback callback);
    }
}
