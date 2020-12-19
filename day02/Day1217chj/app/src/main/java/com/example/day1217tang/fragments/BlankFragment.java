package com.example.day1217tang.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.day1217tang.R;
import com.example.day1217tang.base.BaseFragment;
import com.example.day1217tang.interfaces.INew;
import com.example.day1217tang.model.bean.FindMain;
import com.example.day1217tang.model.bean.SofoMp4;
import com.example.day1217tang.model.bean.SofoPicture;
import com.example.day1217tang.model.bean.SofoText;
import com.example.day1217tang.presenter.PresenterNew;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;


public class BlankFragment extends BaseFragment<PresenterNew> implements INew.View {
private String [] arr = {"图片","视频","文本"};

    @BindView(R.id.tab_sofo_in)
    TabLayout tabSofoIn;
    @BindView(R.id.vp_sofo_in)
    ViewPager vpSofoIn;

    @Override
    protected int getLayout() {
        return R.layout.fragment_blank;
    }

    @Override
    protected PresenterNew createPresenter() {
        return new PresenterNew(this);
    }

    @Override
    protected void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BlankFragment3());
        fragments.add(new BlankFragment4());
        fragments.add(new BlankFragment5());
        vpSofoIn.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabSofoIn.setupWithViewPager(vpSofoIn);
        for (int i = 0; i < arr.length; i++) {
            tabSofoIn.getTabAt(i).setText(arr[i]);
        }
    }

    @Override
    protected void initData() {


    }


    @Override
    public void getSofoPicture(SofoPicture sofoPicture) {

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