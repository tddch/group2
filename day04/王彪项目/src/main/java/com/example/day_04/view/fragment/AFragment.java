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
import com.example.day_04.adapter.AAdapter;
import com.example.day_04.adapter.LeftAdapter;
import com.example.day_04.modle.bean.JingYanBean;
import com.example.day_04.modle.bean.RecyBean;
import com.example.day_04.persenter.HomePersenter;
import com.example.day_04.view.IView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment implements IView {


    private RecyclerView recycler_a;
    private AAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_a, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        HomePersenter homePersenter = new HomePersenter(this);
        homePersenter.start();
    }

    private void initView(View item) {
        recycler_a = item.findViewById(R.id.recycler_a);

        recycler_a.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycler_a.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        adapter = new AAdapter(getActivity());
        recycler_a.setAdapter(adapter);
    }

    @Override
    public void showdui(Object object) {
        if (object instanceof JingYanBean) {
            List<JingYanBean.DataBean.ExpTopBean.ListBean> list = ((JingYanBean) object).getData().getExpTop().getList();
            adapter.additem(list);

        }
    }

    @Override
    public void showcuo(String msg) {

    }
}
