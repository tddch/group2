package com.example.jiguang.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter,T> extends AppCompatActivity implements IBaseView<T> {


    private Unbinder bind;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId != 0)
            setContentView(layoutId);

        bind = ButterKnife.bind(this);

        mPresenter = createPresenter();
        if (mPresenter!=null){
            mPresenter.addView(this);
        }
        init();

        initData();

    }

    protected abstract P createPresenter();

    protected abstract void initData();

    protected abstract void init();

    protected abstract int getLayoutId();
}
