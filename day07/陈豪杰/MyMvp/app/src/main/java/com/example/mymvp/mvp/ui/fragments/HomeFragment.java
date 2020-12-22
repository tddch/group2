package com.example.mymvp.mvp.ui.fragments;

import android.util.Log;
import android.widget.TextView;

import com.example.mymvp.R;
import com.example.mymvp.base.BaseFragment;
import com.example.mymvp.base.BasePresenter;
import com.example.mymvp.mvp.presenter.HomePresenter;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {


    @Override
    protected BasePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.homefrag_item;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getmPresenter().start();
    }

    @Override
    public void onSuccess(Object o) {
        super.onSuccess(o);
//        Log.e("tag","数据++++"+o);
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
//        Log.e("tag",msg);
    }
}
