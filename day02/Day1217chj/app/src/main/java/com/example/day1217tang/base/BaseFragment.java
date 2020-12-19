package com.example.day1217tang.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.day1217tang.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    protected P presenter;
    protected Context context;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View inflate = View.inflate(context, getLayout(), null);
        View inflate = LayoutInflater.from(container.getContext()).inflate(getLayout(), null);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        bind = ButterKnife.bind(this, view);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.onAttachView(this);
        }
        initView();
        initData();
    }


    protected abstract int getLayout();

    protected abstract P createPresenter();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onTips(String tip) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(bind!=null){
            bind.unbind();
        }
        if(presenter!=null){
            presenter.onDetachView();
        }
    }
}
