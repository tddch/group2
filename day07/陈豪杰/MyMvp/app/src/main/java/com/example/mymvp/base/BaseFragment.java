package com.example.mymvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mymvp.base.interfaces.IBaseView;

import java.util.function.Predicate;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public  abstract class BaseFragment<P extends BasePresenter,T> extends Fragment implements IBaseView<T> {
    private P mPresenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        View view = null;
        if (layoutId != 0) {
            view = inflater.inflate(layoutId, null);
            bind = ButterKnife.bind(this, view);
        }
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
        return view;
    }
    protected P getmPresenter() {
        if (mPresenter != null)
            return mPresenter;
        return null;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initData();
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();
    protected abstract void initView();

    protected abstract void initData();
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(bind!=null){
            bind.unbind();
        }
        if(mPresenter!=null){
            mPresenter.detachView();
            mPresenter =null;
        }
    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(String msg) {

    }
}
