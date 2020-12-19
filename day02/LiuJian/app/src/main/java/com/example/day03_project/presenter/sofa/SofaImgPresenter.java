package com.example.day03_project.presenter.sofa;

import android.util.Log;

import com.example.day03_project.base.BasePresenter;
import com.example.day03_project.inesenter.IBaseView;
import com.example.day03_project.inesenter.ICallBack;
import com.example.day03_project.inesenter.sofa.ISofa;
import com.example.day03_project.model.sofa.SofaImgModel;
import com.example.day03_project.model.sofa.data.SofaImgBean;

/**
 *
 */
public class SofaImgPresenter extends BasePresenter<ISofa.View> implements ISofa.Presenter {
    ISofa.View view;
    ISofa.Model model;
    public SofaImgPresenter(ISofa.View view) {
        this.view = view;
        model = new SofaImgModel();
    }


    @Override
    public void getImg() {
        model.loadImg(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){

                    view.getImg((SofaImgBean) o);
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
