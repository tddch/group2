package com.example.day04_2.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.day04_2.HomeActivity;
import com.example.day04_2.R;
import com.example.day04_2.adapter.InAdapter;
import com.example.day04_2.bean.ReWenBean;
import com.example.day04_2.pertor.Pertor;
import com.example.day04_2.view.IView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IView, View.OnClickListener {

    private RecyclerView rec;
    private ArrayList<ReWenBean.DataBean> dataBeans;
    private InAdapter inAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
        initData();
    }

    private void initData() {
        Pertor pertor = new Pertor(this);
        pertor.state();
    }

    private void initView(View view) {
        rec = view.findViewById(R.id.home_rec);
        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);
        TextView tv3 = view.findViewById(R.id.tv3);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
       /* LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);*/
        dataBeans = new ArrayList<>();
        inAdapter = new InAdapter(getActivity(), dataBeans);
        rec.setAdapter(inAdapter);
    }

    @Override
    public void showYes(Object obj) {
        if(obj instanceof  ReWenBean){
            ReWenBean reWenBean= (ReWenBean) obj;
            List<ReWenBean.DataBean> data = reWenBean.getData();
            dataBeans.addAll(data);
            inAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showNot(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
                break;
            case R.id.tv2:
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv3:
                break;
        }
    }
}