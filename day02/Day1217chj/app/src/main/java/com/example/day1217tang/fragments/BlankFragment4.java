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

import butterknife.BindView;


public class BlankFragment4 extends BaseFragment<PresenterNew> implements INew.View {


    @BindView(R.id.recycle_mp4)
    RecyclerView recycleMp4;
    private ArrayList<SofoMp4.DataBeanX.DataBean> mp4list;
    private AdapterMp4SofoRecycle adapterMp4SofoRecycle;

    @Override
    protected int getLayout() {
        return R.layout.fragment_blank4;
    }

    @Override
    protected PresenterNew createPresenter() {
        return new PresenterNew(this);
    }

    @Override
    protected void initView() {
       recycleMp4.setLayoutManager(new LinearLayoutManager(getContext()));
        mp4list = new ArrayList<>();
        adapterMp4SofoRecycle = new AdapterMp4SofoRecycle(context, mp4list);
        recycleMp4.setAdapter(adapterMp4SofoRecycle);
    }

    @Override
    protected void initData() {
        presenter.getSofoMp4();
    }

    @Override
    public void getSofoPicture(SofoPicture sofoPicture) {

    }

    @Override
    public void getSofoMp4(SofoMp4 sofoMp4) {
        mp4list.clear();
        mp4list.addAll(sofoMp4.getData().getData());
        adapterMp4SofoRecycle.notifyDataSetChanged();
    }

    @Override
    public void getSofoText(SofoText sofoText) {

    }

    @Override
    public void getFindMain(FindMain findMain) {

    }
}