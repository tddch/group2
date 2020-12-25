package com.example.day_07.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day_07.R;
import com.example.day_07.adapter.LeftAdapter;
import com.example.day_07.model.bean.RecyBean;
import com.example.day_07.persenter.HomePersenter;
import com.example.day_07.view.IView;
import com.example.day_07.view.activity.BaiDuActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment implements IView {


    private RecyclerView recycler_left;
    private LeftAdapter adapter;
    private LinearLayout mBaiDu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_left, container, false);
        initView(inflate);
        initData();
        return inflate;
    }


    private void initData() {
        HomePersenter homePersenter = new HomePersenter(this);
        homePersenter.start();
    }


    private void initView(View item) {
        recycler_left = item.findViewById(R.id.recycler_left);
        mBaiDu = item.findViewById(R.id.layout_paihang);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycler_left.setLayoutManager(linearLayoutManager);

        recycler_left.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        adapter = new LeftAdapter(getActivity());
        recycler_left.setAdapter(adapter);


        //跳转到百度
        mBaiDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BaiDuActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void showdui(Object object) {
        if (object instanceof RecyBean) {
            List<RecyBean.DataBean> data = ((RecyBean) object).getData();
            adapter.additem(data);
        }
    }

    @Override
    public void showcuo(String msg) {

    }
}
