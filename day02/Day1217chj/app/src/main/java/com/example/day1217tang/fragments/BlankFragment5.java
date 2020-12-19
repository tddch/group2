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


public class BlankFragment5 extends BaseFragment<PresenterNew> implements INew.View {


    @BindView(R.id.recy_text)
    RecyclerView recyText;
    private ArrayList<SofoText.DataBeanX.DataBean> textlist;
    private AdapterTextSofoRecycle adapterTextSofoRecycle;

    @Override
    protected int getLayout() {
        return R.layout.fragment_blank5;
    }

    @Override
    protected PresenterNew createPresenter() {
        return new PresenterNew(this);
    }

    @Override
    protected void initView() {
        recyText.setLayoutManager(new LinearLayoutManager(getContext()));
        textlist = new ArrayList<>();
        adapterTextSofoRecycle = new AdapterTextSofoRecycle(getContext(), textlist);
        recyText.setAdapter(adapterTextSofoRecycle);
    }

    @Override
    protected void initData() {
        presenter.getSofoText();
    }

    @Override
    public void getSofoPicture(SofoPicture sofoPicture) {

    }

    @Override
    public void getSofoMp4(SofoMp4 sofoMp4) {

    }

    @Override
    public void getSofoText(SofoText sofoText) {
        textlist.clear();
        textlist.addAll(sofoText.getData().getData());
        adapterTextSofoRecycle.notifyDataSetChanged();
    }

    @Override
    public void getFindMain(FindMain findMain) {

    }
}