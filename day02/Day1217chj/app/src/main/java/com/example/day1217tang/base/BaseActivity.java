package com.example.day1217tang.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.day1217tang.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter>extends AppCompatActivity implements IBaseView {
    protected P presenter;
    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bind = ButterKnife.bind(this);
        initView();
        presenter = createPresenter();
        if(presenter!=null){
            presenter.onAttachView(this);
        }
        initData();
    }




    protected abstract int getLayout();
    protected abstract void initView();
    protected abstract P createPresenter();
    protected abstract void initData();
    @Override
    public void onTips(String tip) {
        Log.e("tag",tip);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bind!=null){
            bind.unbind();
        }
        if(presenter!=null){
            presenter.onDetachView();
        }
    }

}
