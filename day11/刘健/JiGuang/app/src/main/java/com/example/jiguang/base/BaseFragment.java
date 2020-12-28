package com.example.jiguang.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter,T> extends Fragment implements IBaseView<T> {

    private Unbinder mBind;
    private P mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        View view = null;
        if (layoutId != 0)
            view = inflater.inflate(layoutId,container,false);
        mBind = ButterKnife.bind(this, view);
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.addView(this);
        init();
        initData();
        return view;
    }

    protected abstract void init();

    protected abstract void initData();

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

}
