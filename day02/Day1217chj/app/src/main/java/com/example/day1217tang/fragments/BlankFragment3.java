package com.example.day1217tang.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day1217tang.R;
import com.example.day1217tang.base.BaseFragment;
import com.example.day1217tang.interfaces.INew;
import com.example.day1217tang.model.bean.FindMain;
import com.example.day1217tang.model.bean.SofoMp4;
import com.example.day1217tang.model.bean.SofoPicture;
import com.example.day1217tang.model.bean.SofoText;
import com.example.day1217tang.presenter.PresenterNew;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class BlankFragment3 extends BaseFragment<PresenterNew> implements INew.View {


    @BindView(R.id.recy_picture)
    RecyclerView recyPicture;
    private ArrayList<SofoPicture.DataBeanX.DataBean> pictlist;
    private AdapterPctureSofoRecycle adapterPctureSofoRecycle;

    @Override
    protected int getLayout() {
        return R.layout.fragment_blank3;
    }

    @Override
    protected PresenterNew createPresenter() {
        return new PresenterNew(this);
    }

    @Override
    protected void initView() {
        initPicture();
        initMp4();
    }

    private void initMp4() {

    }

    private void initPicture() {
        recyPicture.setLayoutManager(new LinearLayoutManager(getContext()));
        pictlist = new ArrayList<>();

        adapterPctureSofoRecycle = new AdapterPctureSofoRecycle(getContext(), pictlist);
        recyPicture.setAdapter(adapterPctureSofoRecycle);
    }

    @Override
    protected void initData() {
        presenter.getSofoText();
        presenter.getSofoMp4();
        presenter.getFindMain();
        presenter.getSofoPicture();
    }

    @Override
    public void getSofoPicture(SofoPicture sofoPicture) {
        pictlist.clear();
        List<SofoPicture.DataBeanX.DataBean> data = sofoPicture.getData().getData();
        pictlist.addAll(data);
        adapterPctureSofoRecycle.notifyDataSetChanged();
    }

    @Override
    public void getSofoMp4(SofoMp4 sofoMp4) {

    }

    @Override
    public void getSofoText(SofoText sofoText) {

    }

    @Override
    public void getFindMain(FindMain findMain) {

    }
}