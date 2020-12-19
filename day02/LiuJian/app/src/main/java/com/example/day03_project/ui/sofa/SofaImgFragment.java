package com.example.day03_project.ui.sofa;

import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day03_project.R;
import com.example.day03_project.base.BaseFragment;
import com.example.day03_project.inesenter.sofa.ISofa;
import com.example.day03_project.model.sofa.data.SofaImgBean;
import com.example.day03_project.presenter.sofa.SofaImgPresenter;
import com.example.day03_project.ui.sofa.adapter.SofaImgAdapter;

import java.util.ArrayList;

import butterknife.BindView;


public class SofaImgFragment extends BaseFragment<SofaImgPresenter> implements ISofa.View {


    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<SofaImgBean.DataBeanX.DataBean> list;
    private SofaImgAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_sofa_img;
    }

    @Override
    public void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new SofaImgAdapter(list, getActivity());
        rv.setAdapter(adapter);
    }

    @Override
    public SofaImgPresenter createPresenter() {
        return new SofaImgPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getImg();
    }

    @Override
    public void getImg(SofaImgBean imgBean) {
        Log.e("TAG", "getImg: " + imgBean.toString());
        if(imgBean.getData().getData()!=null){
            list.addAll(imgBean.getData().getData());
            adapter.notifyDataSetChanged();
        }
    }
}