package com.example.mymvp.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymvp.base.interfaces.IBasePresenter;
import com.example.mymvp.base.interfaces.IBaseView;
import com.example.mymvp.manager.ContainManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter,T> extends AppCompatActivity implements IBaseView<T> {

    private Unbinder bind;
    private P mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
       if(layoutId!=0){
           setContentView(layoutId);
       }
        bind = ButterKnife.bind(this);
        ContainManager.getmManager()
                .addActivity(this);
        mPresenter = createPresenter();
        initView();
        initData();
    }



    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract P createPresenter();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
      if(bind!=null){
          bind.unbind();
      }
      if(mPresenter!=null){
          mPresenter.detachView();
          mPresenter = null;
      }

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(String msg) {

    }

}
