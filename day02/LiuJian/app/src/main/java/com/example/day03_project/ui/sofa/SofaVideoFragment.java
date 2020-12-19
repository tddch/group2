package com.example.day03_project.ui.sofa;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day03_project.R;
import com.example.day03_project.base.BaseFragment;
import com.example.day03_project.inesenter.sofa.ISodaVideo;
import com.example.day03_project.model.sofa.data.SofaVideoBean;
import com.example.day03_project.presenter.sofa.SofaVideoPresenter;
import com.example.day03_project.ui.sofa.adapter.SofaVideoAdapter;

import java.security.DomainLoadStoreParameter;
import java.util.ArrayList;

import butterknife.BindView;


public class SofaVideoFragment extends BaseFragment<SofaVideoPresenter> implements ISodaVideo.View {


    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<SofaVideoBean.DataBeanX.DataBean> list;
    private SofaVideoAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_sofa_video;
    }

    @Override
    public void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new SofaVideoAdapter(list, getActivity());
        rv.setAdapter(adapter);
    }

    @Override
    public SofaVideoPresenter createPresenter() {
        return new SofaVideoPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getVideo();
    }

    @Override
    public void getVideo(SofaVideoBean sofaVideoBean) {
        if(sofaVideoBean.getData().getData()!=null){
            list.addAll(sofaVideoBean.getData().getData());
            adapter.notifyDataSetChanged();
        }
    }
}