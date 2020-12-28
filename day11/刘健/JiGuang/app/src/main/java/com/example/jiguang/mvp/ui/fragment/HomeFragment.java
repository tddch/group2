package com.example.jiguang.mvp.ui.fragment;

import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.jiguang.R;
import com.example.jiguang.base.BaseFragment;
import com.example.jiguang.base.BasePresenter;
import com.example.jiguang.mvp.model.bean.BannerBean;
import com.example.jiguang.mvp.presenter.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;

import butterknife.BindView;


public class HomeFragment extends BaseFragment {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_chuxing)
    RecyclerView rvChuxing;
    @BindView(R.id.rv_haiwai)
    RecyclerView rvHaiwai;
    @BindView(R.id.rv_guo)
    RecyclerView rvGuo;
    @BindView(R.id.iv_item_dao)
    ImageView ivItemDao;
    private ArrayList<BannerBean> list;
    private homeAdapter adapter;
    private com.example.jiguang.mvp.ui.fragment.hwAdapter hwAdapter;
    private GuoAdapter guoAdapter;

    @Override
    protected void init() {
        Glide.with(getActivity()).load(R.drawable.e2).apply(new RequestOptions().transform(new RoundedCorners(30))).into(ivItemDao);

        rvChuxing.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvHaiwai.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvGuo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        list = new ArrayList<>();
        adapter = new homeAdapter(list, getActivity());
        hwAdapter = new hwAdapter(list, getActivity());
        guoAdapter = new GuoAdapter(list, getActivity());
        rvHaiwai.setAdapter(hwAdapter);
        rvChuxing.setAdapter(adapter);
        rvGuo.setAdapter(guoAdapter);
    }

    @Override
    protected void initData() {
        createPresenter().start();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void getData(Object o) {
        if (o != null) {

            ArrayList<BannerBean> bannerBeans = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                if(i==0){
                    bannerBeans.add(i, new BannerBean(R.drawable.e1, "啊巴啊巴" + i));

                }
                if(i==1){
                    bannerBeans.add(i, new BannerBean(R.drawable.e2, "啊巴啊巴" + i));

                }
                if(i==2){
                    bannerBeans.add(i, new BannerBean(R.drawable.e3, "啊巴啊巴" + i));

                }
                if(i==3){
                    bannerBeans.add(i, new BannerBean(R.drawable.e4, "啊巴啊巴" + i));

                }
            }



            list.addAll(bannerBeans);
            hwAdapter.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
            guoAdapter.notifyDataSetChanged();


            Log.e("TAG", "getData: " + bannerBeans.get(1).getTitle());
            banner.setAdapter(new BannerImageAdapter<BannerBean>(bannerBeans) {
                @Override
                public void onBindView(BannerImageHolder holder, BannerBean data, int position, int size) {
                    Glide.with(holder.imageView).load(data.getImage())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                            .into(holder.imageView);
                }
            })
                    .setScrollTime(3000)
                    .isAutoLoop(true)
                    .addBannerLifecycleObserver(this)//添加生命周期观察者
                    .setIndicator(new CircleIndicator(getActivity()))
                    .start();

        } else {
            Log.e("TAG", "getData: null");
        }

    }


}