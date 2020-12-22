package com.example.mymvp.mvp.ui.fragments;

import android.util.Log;

import com.example.mymvp.R;
import com.example.mymvp.base.BaseFragment;
import com.example.mymvp.base.BasePresenter;
import com.example.mymvp.mvp.presenter.FindPresenter;
import com.example.mymvp.mvp.presenter.HomePresenter;

public class FindFragment extends BaseFragment {
    @Override
    protected BasePresenter createPresenter() {
        return new FindPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.findfrag_item;
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
//        Log.e("tag",o+"**********");
    }
}
