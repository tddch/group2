package com.example.day03_project.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.day03_project.inesenter.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 */
public abstract class BaseFragment <P extends BasePresenter> extends Fragment implements IBaseView {
    protected P presenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(getLayout(), null);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter=createPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
        initView();

        initData();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract P createPresenter();

    public abstract void initData();


    @Override
    public void tips(String msg) {

    }

    @Override
    public void loading(int visible) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }

        if (presenter!=null){
            presenter.unAttachView();
        }
    }
}
