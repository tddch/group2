package com.example.liujian.ui.discover;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujian.R;
import com.example.liujian.base.BaseFragment;
import com.example.liujian.bean.DiscoverTabRvBean;
import com.example.liujian.inesenter.discover.IDiscoverTab;
import com.example.liujian.presnter.tongpao.discover.DiscoverTabPresenter;

import java.util.ArrayList;

import butterknife.BindView;


public class TabRvFragment extends BaseFragment<DiscoverTabPresenter> implements IDiscoverTab.View {
    private int type;
    @BindView(R.id.rv_tabrv)
    RecyclerView rvTabrv;
    private ArrayList<DiscoverTabRvBean.DataBean.ListBean> listBeans;
    private TabRvAdapter adapter;

    public TabRvFragment(int type) {
        this.type = type;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_tab_rv;
    }

    @Override
    public void initView() {
        rvTabrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTabrv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        listBeans = new ArrayList<>();
        adapter = new TabRvAdapter(listBeans, getActivity());
        rvTabrv.setAdapter(adapter);
    }

    @Override
    public DiscoverTabPresenter createPresenter() {
        return new DiscoverTabPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getTabRv(type);
    }

    @Override
    public void getTabRvBean(DiscoverTabRvBean tabRvBean) {
        Log.e("TAG", "getTabRvBean: "+tabRvBean );
        listBeans.addAll(tabRvBean.getData().getList());
        adapter.notifyDataSetChanged();

    }
}
