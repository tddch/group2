package com.example.liujian.ui.discover.paihang;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liujian.R;
import com.example.liujian.net.CommonSubscriber;
import com.example.liujian.net.HttpManager;
import com.example.liujian.ui.discover.paihang.adapter.FindMoneyAdapter;
import com.example.liujian.ui.discover.paihang.bean.ListBean;
import com.example.liujian.ui.discover.paihang.bean.MoneyBean;
import com.example.liujian.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MoneyFragment extends Fragment {

    @BindView(R.id.rv)
    RecyclerView money_rv;
    private Unbinder unbinder;
    private List<ListBean> listBeans;
    private FindMoneyAdapter findMoneyAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        HttpManager.getInstance().tongpaoApi().getFindmoney()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoneyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoneyBean moneyBean) {
                        listBeans.addAll(moneyBean.getData().getTongQianTop().getList());
                        findMoneyAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        money_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        listBeans = new ArrayList<>();
        findMoneyAdapter = new FindMoneyAdapter(getActivity(), listBeans);
        money_rv.setAdapter(findMoneyAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}