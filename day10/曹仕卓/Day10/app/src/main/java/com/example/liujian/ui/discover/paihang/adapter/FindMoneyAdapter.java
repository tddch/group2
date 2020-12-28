package com.example.liujian.ui.discover.paihang.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.liujian.R;
import com.example.liujian.base.BaseAdapter;
import com.example.liujian.ui.discover.paihang.bean.ListBean;

import java.util.List;

public class FindMoneyAdapter extends BaseAdapter<ListBean> {
    public FindMoneyAdapter(Context context, List<ListBean> data) {
        super(data, context);
    }

    @Override
    protected void bindData(ListBean data, VH baseVirwHolder, int position, Context context) {

        TextView ranking_tv_ranking = (TextView) baseVirwHolder.getViewById(R.id.ranking_tv_ranking);
        ImageView ranking_tv_header = (ImageView) baseVirwHolder.getViewById(R.id.ranking_tv_header);
        TextView ranking_tv_name = (TextView) baseVirwHolder.getViewById(R.id.ranking_tv_name);
        ImageView ranking_tv_level = (ImageView) baseVirwHolder.getViewById(R.id.ranking_tv_level);
        TextView ranking_tv_sex = (TextView) baseVirwHolder.getViewById(R.id.ranking_tv_sex);
        TextView ranking_tv_location = (TextView) baseVirwHolder.getViewById(R.id.ranking_tv_location);
        TextView ranking_tv_money = (TextView) baseVirwHolder.getViewById(R.id.ranking_tv_money);
        ListBean listBean = data;
        ranking_tv_ranking.setText(listBean.getRankType() + "");
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(listBean.getHeadUrl()).apply(requestOptions).into(ranking_tv_header);
        ranking_tv_name.setText(listBean.getNickName());
        ranking_tv_sex.setText(listBean.getSex());
        ranking_tv_location.setText(listBean.getProvince());
        ranking_tv_money.setText(listBean.getTongQian() + "铜钱");



        if (listBean.getLevel() == 1) {
            ranking_tv_level.setImageResource(R.mipmap.lv1);
        } else if (listBean.getLevel() == 2) {
            ranking_tv_level.setImageResource(R.mipmap.lv2);
        } else if (listBean.getLevel() == 3) {
            ranking_tv_level.setImageResource(R.mipmap.lv3);
        } else if (listBean.getLevel() == 4) {
            ranking_tv_level.setImageResource(R.mipmap.lv4);
        } else if (listBean.getLevel() == 5) {
            ranking_tv_level.setImageResource(R.mipmap.lv5);
        } else if (listBean.getLevel() == 6) {
            ranking_tv_level.setImageResource(R.mipmap.lv6);
        } else if (listBean.getLevel() == 7) {
            ranking_tv_level.setImageResource(R.mipmap.lv7);
        } else if (listBean.getLevel() == 8) {
            ranking_tv_level.setImageResource(R.mipmap.lv8);

        }

    }

    @Override
    protected int getLayout() {
        return R.layout.findmoney_item;
    }

}
