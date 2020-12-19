package com.example.day_04.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day_04.R;
import com.example.day_04.adapter.BAdapter;
import com.example.day_04.adapter.CAdapter;
import com.example.day_04.modle.bean.JingYanBean;
import com.example.day_04.modle.bean.QianDaoBean;
import com.example.day_04.persenter.HomePersenter;
import com.example.day_04.view.IView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CFragment extends Fragment implements IView {


    private RecyclerView recycler_c;

    private CAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_c, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        HomePersenter homePersenter = new HomePersenter(this);
        homePersenter.start();
    }

    private void initView(View item) {
        recycler_c = item.findViewById(R.id.recycler_c);

        recycler_c.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycler_c.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        adapter = new CAdapter(getActivity());
        recycler_c.setAdapter(adapter);
    }


    @Override
    public void showdui(Object object) {
        if (object instanceof QianDaoBean) {
            List<QianDaoBean.DatasBean.ListBean> list = ((QianDaoBean) object).getData().getList();
            adapter.additem(list);
        }
    }

    @Override
    public void showcuo(String msg) {

    }
}
